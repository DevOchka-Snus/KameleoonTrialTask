package com.example.kameleoontrialtask.repo;

import com.example.kameleoontrialtask.domain.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends CrudRepository<Vote, Long> {
    Optional<Vote> findByStatusAndAuthorAndQuote(Status status, User author, Quote quote);
    void deleteByStatusAndAuthorAndQuote(Status status, User author, Quote quote);
    List<Vote> findAllByAuthorOrderByExpirationDateDesc(User author);
    List<Vote> findAllByQuote(Quote quote);
}
