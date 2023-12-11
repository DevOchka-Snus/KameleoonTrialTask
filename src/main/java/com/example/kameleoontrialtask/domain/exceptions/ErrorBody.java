package com.example.kameleoontrialtask.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorBody {
    private String message;

    private Map<String, String> errors;

    public ErrorBody(String message) {
        this.message = message;
    }
}
