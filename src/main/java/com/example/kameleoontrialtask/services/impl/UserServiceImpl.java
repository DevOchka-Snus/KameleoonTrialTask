package com.example.kameleoontrialtask.services.impl;

import com.example.kameleoontrialtask.domain.exceptions.ResourceNotFoundException;
import com.example.kameleoontrialtask.domain.models.Quote;
import com.example.kameleoontrialtask.domain.models.User;
import com.example.kameleoontrialtask.domain.models.Vote;
import com.example.kameleoontrialtask.repo.UserRepository;
import com.example.kameleoontrialtask.services.QuoteService;
import com.example.kameleoontrialtask.services.UserService;
import com.example.kameleoontrialtask.web.dtos.VoteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final QuoteService quoteService;
    @Override
    @Transactional(readOnly = true)
    public User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("User already exists");
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        var userToUpdate = userRepository.findById(user.getId());
        if (userToUpdate.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("Email is used");
        }
        var updatedUser = userToUpdate.get();
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Quote createQuote(Quote quote, Long authorId) {
        var author = userRepository.findById(authorId);
        if (author.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        quote.setAuthor(author.get());
        return quoteService.addQuote(quote);
    }

    @Override
    @Transactional
    public Quote updateQuote(Quote quote, Long authorID) {
        if (!userRepository.existsById(authorID)) {
            throw new ResourceNotFoundException("User not found");
        }
        return quoteService.updateQuote(quote);
    }

    @Override
    @Transactional
    public Optional<Vote> clickVote(VoteDto voteDto) {
        var author = userRepository.findById(voteDto.getAuthorId());
        if (author.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        var quote = quoteService.getQuote(voteDto.getQuoteId());
        var vote = new Vote(voteDto.getStatus(), author.get(), quote, voteDto.getExpirationDate());
        return quoteService.clickVote(vote);
    }

    @Override
    @Transactional
    public List<Quote> getLastQuotes(Long userId) {
        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        return quoteService.getLastVotes(user.get());
    }
}
