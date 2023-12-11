package com.example.kameleoontrialtask.web.dtos;

import com.example.kameleoontrialtask.domain.models.User;
import com.example.kameleoontrialtask.web.validation.OnCreate;
import com.example.kameleoontrialtask.web.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuoteDto {
    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "Content must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 256, message = "Content must be smaller than 256", groups = {OnCreate.class, OnUpdate.class})
    private String content;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatingDate;
}
