package com.example.kameleoontrialtask.services;

import com.example.kameleoontrialtask.domain.models.Quote;
import com.example.kameleoontrialtask.domain.models.Status;
import com.example.kameleoontrialtask.domain.models.User;
import com.example.kameleoontrialtask.domain.models.Vote;

import java.util.List;
import java.util.Optional;

public interface QuoteService {
    Quote getQuote(long id);
    Quote getRandomQuote();
    List<Quote> getAllQuotes();
    Quote addQuote(Quote quote);
    Quote updateQuote(Quote quote);
    void deleteQuote(long id);
    Optional<Vote> clickVote(Vote vote);
    List<Quote> getLastVotes(User author);
    List<Quote> getTop10BestQuotes();
    List<Quote> getTop10WorstQuotes();
}
