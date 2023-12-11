package com.example.kameleoontrialtask.services;

import com.example.kameleoontrialtask.domain.models.Quote;
import com.example.kameleoontrialtask.domain.models.User;
import com.example.kameleoontrialtask.domain.models.Vote;
import com.example.kameleoontrialtask.web.dtos.VoteDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUser(long id);
    User getUserByEmail(String email);
    User create(User user);
    User update(User user);
    void delete(Long id);
    Quote createQuote(Quote quote, Long authorID);
    Quote updateQuote(Quote quote, Long authorID);
    Optional<Vote> clickVote(VoteDto voteDto);
    List<Quote> getLastQuotes(Long userId);
}
