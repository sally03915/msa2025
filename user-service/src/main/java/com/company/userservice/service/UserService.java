package com.company.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.company.userservice.dto.UserDto;
import com.company.userservice.jpa.UserEntity;

public interface UserService   extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();

    UserDto getUserDetailsByEmail(String userName);
}
