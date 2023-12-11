package com.example.kameleoontrialtask.web.cotrollers;

import com.example.kameleoontrialtask.domain.models.User;
import com.example.kameleoontrialtask.services.UserService;
import com.example.kameleoontrialtask.web.dtos.QuoteDto;
import com.example.kameleoontrialtask.web.dtos.UserDto;
import com.example.kameleoontrialtask.web.dtos.VoteDto;
import com.example.kameleoontrialtask.web.mappers.QuoteMapper;
import com.example.kameleoontrialtask.web.mappers.UserMapper;
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
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final QuoteMapper quoteMapper;

    @PostMapping
    public ResponseEntity<?> create(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        var result = userService.create(user);
        return ResponseEntity.ok(result);
    }
    @PutMapping
    public ResponseEntity<?> update(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        var result = userService.update(user);
        return ResponseEntity.ok(userMapper.toDto(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/quotes")
    public ResponseEntity<?> createQuote(@PathVariable("id") Long id, @Validated(OnCreate.class) @RequestBody QuoteDto quoteDto) {
        var quote = quoteMapper.toEntity(quoteDto);
        var result = userService.createQuote(quote, id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/quotes")
    public ResponseEntity<?> updateQuote(@PathVariable("id") Long id, @Validated(OnUpdate.class) @RequestBody QuoteDto quoteDto) {
        var quote = quoteMapper.toEntity(quoteDto);
        var result = userService.updateQuote(quote, id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/voting")
    public ResponseEntity<?> clickVote(@Validated @RequestBody VoteDto voteDto) {
        var result = userService.clickVote(voteDto);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/{id}/last")
    public ResponseEntity<?> getLastQuotes(@PathVariable("id") Long id) {
        var result = userService.getLastQuotes(id);
        return ResponseEntity.ok(result);
    }
}
