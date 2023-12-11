package com.example.kameleoontrialtask.web.cotrollers;

import com.example.kameleoontrialtask.domain.exceptions.ErrorBody;
import com.example.kameleoontrialtask.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBody handleResourceNotFound(ResourceNotFoundException e) {
        return new ErrorBody(e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBody handleIllegalState(IllegalStateException e) {
        return new ErrorBody(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBody handleIllegalArgument(IllegalArgumentException e) {
        return new ErrorBody(e.getMessage());
    }
}
