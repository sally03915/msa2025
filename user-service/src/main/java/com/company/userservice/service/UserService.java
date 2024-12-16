package com.company.userservice.service;

import com.company.userservice.dto.UserDto;
import com.company.userservice.jpa.UserEntity;

public interface UserService   {
    UserDto createUser(UserDto userDto);
    
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
}
