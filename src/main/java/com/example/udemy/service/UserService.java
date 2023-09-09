package com.example.udemy.service;

import com.example.udemy.shared.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(String uid);

    UserDto updateUser(UserDto userDto,String uid);
    UserDto deleteUser(String uid);

    List<UserDto> getUsers(int page, int limit);

}
