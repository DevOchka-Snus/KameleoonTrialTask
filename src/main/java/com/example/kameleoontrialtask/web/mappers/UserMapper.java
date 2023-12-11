package com.example.kameleoontrialtask.web.mappers;

import com.example.kameleoontrialtask.domain.models.User;
import com.example.kameleoontrialtask.web.dtos.UserDto;
import org.mapstruct.Mapper;

public interface UserMapper extends Mappable<User, UserDto> {
}
