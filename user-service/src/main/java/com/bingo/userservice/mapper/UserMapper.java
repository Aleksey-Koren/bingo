package com.bingo.userservice.mapper;

import com.bingo.userservice.dto.UserDto;
import com.bingo.userservice.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .title(user.getTitle())
                .build();
    }

    public User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId() != null ? dto.getId() : null)
                .title(dto.getTitle())
                .build();
    }
}
