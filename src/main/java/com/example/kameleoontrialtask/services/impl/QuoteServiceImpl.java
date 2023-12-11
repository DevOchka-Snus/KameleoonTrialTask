package com.example.kameleoontrialtask.services.impl;

import com.example.kameleoontrialtask.domain.exceptions.ResourceNotFoundException;
import com.example.kameleoontrialtask.domain.models.Quote;
import com.example.kameleoontrialtask.domain.models.Status;
import com.example.kameleoontrialtask.domain.models.User;
import com.example.kameleoontrialtask.domain.models.Vote;
import com.example.kameleoontrialtask.repo.QuoteRepository;
import com.example.kameleoontrialtask.repo.VoteRepository;
import com.example.kameleoontrialtask.services.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final VoteRepository voteRepository;
    private final QuoteRepository quoteRepository;
    @Override
    @Transactional(readOnly = true)
    public Quote getQuote(long id) {
        var quote = quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found"));
        var votes = voteRepository.findAllByQuote(quote);
        quote.setVotes(votes);
        return quote;
    }

    @Override
    @Transactional(readOnly = true)
    public Quote getRandomQuote() {
        var randomQuote = quoteRepository.getRandomQuote().orElse(null);
        if (randomQuote != null) {
            randomQuote.setVotes(voteRepository.findAllByQuote(randomQuote));
        }
        return randomQuote;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Quote> getAllQuotes() {
        var quotes = quoteRepository.findAll();
        quotes.forEach(quote -> {
            quote.setVotes(voteRepository.findAllByQuote(quote));
        });
        return quotes;
    }

    @Override
    @Transactional
    public Quote addQuote(Quote quote) {
        quote.setRating(0L);
        quote.setExpirationDate(quote.getUpdatingDate());
        return quoteRepository.save(quote);
    }

    @Override
    @Transactional
    public Quote updateQuote(Quote quote) {
        var quoteToUpdate = quoteRepository.findById(quote.getId());
        if (quoteToUpdate.isEmpty()) {
            throw new ResourceNotFoundException("Quote not found");
        }
        var updatedQuote = quoteToUpdate.get();
        updatedQuote.setContent(quote.getContent());
        updatedQuote.setUpdatingDate(quote.getUpdatingDate());
        return quoteRepository.save(updatedQuote);
    }

    @Override
    @Transactional
    public void deleteQuote(long id) {
        quoteRepository.deleteById(id);
    }
    @Override
    @Transactional
    public Optional<Vote> clickVote(Vote vote) {
        if (voteRepository.findByStatusAndAuthorAndQuote(vote.getStatus(), vote.getAuthor(), vote.getQuote()).isPresent()) {
            voteRepository.deleteByStatusAndAuthorAndQuote(vote.getStatus(), vote.getAuthor(), vote.getQuote());
            deleteVote(vote.getQuote(), vote.getStatus());
            return Optional.empty();
        }
        var inversionVote = voteRepository.findByStatusAndAuthorAndQuote(vote.getStatus().inverse(), vote.getAuthor(), vote.getQuote());
        if (inversionVote.isPresent()) {
            voteRepository.delete(inversionVote.get());
            replaceVote(vote.getQuote(), vote.getStatus());
        } else {
            addVote(vote.getQuote(), vote.getStatus());
        }
        voteRepository.save(vote);
        return Optional.of(vote);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Quote> getLastVotes(User author) {
        var votes = voteRepository.findAllByAuthorOrderByExpirationDateDesc(author);
        List<Quote> quotes = new ArrayList<>();
        votes.stream().limit(5).forEach(vote -> {
            vote.getQuote().setVotes(voteRepository.findAllByQuote(vote.getQuote()));
            quotes.add(vote.getQuote());
        });
        return quotes;
    }
    @Transactional
    public void addVote(Quote quote, Status status) {
        quote.setRating(quote.getRating() + (status.equals(Status.LIKE) ? 1 : -1));
        quoteRepository.save(quote);
    }

    @Transactional
    public void replaceVote(Quote quote, Status status) {
        quote.setRating(quote.getRating() + (status.equals(Status.LIKE) ? 2 : -2));
        quoteRepository.save(quote);
    }

    @Transactional
    public void deleteVote(Quote quote, Status status) {
        quote.setRating(quote.getRating() + (status.equals(Status.LIKE) ? -1 : 1));
        quoteRepository.save(quote);
    }

    @Override
    @Transactional
    public List<Quote> getTop10BestQuotes() {
        return quoteRepository.getBestQuotes();
    }

    @Override
    @Transactional
    public List<Quote> getTop10WorstQuotes() {
        return quoteRepository.getWorstQuotes();
    }
}
