package com.example.kameleoontrialtask.web.cotrollers;

import com.example.kameleoontrialtask.services.QuoteService;
import com.example.kameleoontrialtask.web.dtos.QuoteDto;
import com.example.kameleoontrialtask.web.dtos.VoteDto;
import com.example.kameleoontrialtask.web.mappers.QuoteMapper;
import com.example.kameleoontrialtask.web.validation.OnCreate;
import com.example.kameleoontrialtask.web.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
@RequiredArgsConstructor
@Validated
@RequestMapping("api/v1/quotes")
public class QuoteController {
    private final QuoteService quoteService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuote(@PathVariable("id") Long id) {
        var result = quoteService.getQuote(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/random")
    public ResponseEntity<?> getRandomQuote() {
        var result = quoteService.getRandomQuote();
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getAllQuotes() {
        return ResponseEntity.ok(quoteService.getAllQuotes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuote(@PathVariable("id") Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("/best_quotes")
    public ResponseEntity<?> getTop10BestQuotes() {
        return ResponseEntity.ok(quoteService.getTop10BestQuotes());
    }

    @GetMapping("/worst_quotes")
    public ResponseEntity<?> getTop10WorstQuotes() {
        return ResponseEntity.ok(quoteService.getTop10WorstQuotes());
    }
}
