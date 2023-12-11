package com.example.kameleoontrialtask.repo;

import com.example.kameleoontrialtask.domain.models.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends CrudRepository<Quote, Long> {
    @Query(value = """
            SELECT * FROM quotes ORDER BY RAND() LIMIT 1
    """, nativeQuery = true)
    Optional<Quote> getRandomQuote();
    @Query(value = """
            SELECT * FROM quotes
    """, nativeQuery = true)
    List<Quote> findAll();
    @Query(value = """
            SELECT * FROM quotes ORDER BY rating DESC LIMIT 10
    """, nativeQuery = true)
    List<Quote> getBestQuotes();
    @Query(value = """
            SELECT * FROM quotes ORDER BY rating LIMIT 10
    """, nativeQuery = true)
    List<Quote> getWorstQuotes();

}
