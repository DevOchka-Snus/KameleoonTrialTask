package com.example.kameleoontrialtask.web.dtos;

import com.example.kameleoontrialtask.domain.models.Status;
import com.example.kameleoontrialtask.web.validation.OnCreate;
import com.example.kameleoontrialtask.web.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class VoteDto {
    @NotNull(message = "Status must be not null", groups = {OnCreate.class, OnUpdate.class})
    private Status status;
    @NotNull(message = "authorId must be not null", groups = {OnCreate.class, OnUpdate.class})
    private Long authorId;
    @NotNull(message = "quoteId must be not null", groups = {OnCreate.class, OnUpdate.class})
    private Long quoteId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;
}
