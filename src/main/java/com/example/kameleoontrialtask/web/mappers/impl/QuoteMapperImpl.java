package com.example.kameleoontrialtask.web.mappers.impl;

import com.example.kameleoontrialtask.domain.models.Quote;
import com.example.kameleoontrialtask.web.dtos.QuoteDto;
import com.example.kameleoontrialtask.web.mappers.QuoteMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuoteMapperImpl implements QuoteMapper {
    @Override
    public QuoteDto toDto(Quote entity) {
        QuoteDto dto = new QuoteDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setUpdatingDate(entity.getUpdatingDate());
        return dto;
    }

    @Override
    public List<QuoteDto> toDto(List<Quote> entities) {
        List<QuoteDto> dtos = new ArrayList<>();
        entities.forEach(quote -> dtos.add(toDto(quote)));
        return dtos;
    }

    @Override
    public Quote toEntity(QuoteDto dto) {
        Quote entity = new Quote();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setUpdatingDate(dto.getUpdatingDate());
        return entity;
    }

    @Override
    public List<Quote> toEntity(List<QuoteDto> dtos) {
        List<Quote> entities = new ArrayList<>();
        dtos.forEach(quoteDto -> entities.add(toEntity(quoteDto)));
        return entities;
    }
}
