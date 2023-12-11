package com.example.kameleoontrialtask.web.mappers.impl;

import com.example.kameleoontrialtask.domain.models.User;
import com.example.kameleoontrialtask.web.dtos.UserDto;
import com.example.kameleoontrialtask.web.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setEmail(entity.getEmail());
        userDto.setPassword(entity.getPassword());
        userDto.setExpirationDate(entity.getExpirationDate());
        return userDto;
    }

    @Override
    public List<UserDto> toDto(List<User> entities) {
        List<UserDto> userDtos = new ArrayList<>();
        entities.forEach(user -> {userDtos.add(toDto(user));});
        return userDtos;
    }

    @Override
    public User toEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setExpirationDate(dto.getExpirationDate());
        return entity;
    }

    @Override
    public List<User> toEntity(List<UserDto> dtos) {
        List<User> entities = new ArrayList<>();
        dtos.forEach(userDto -> entities.add(toEntity(userDto)));
        return entities;
    }
}
