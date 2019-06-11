package com.epam.cdp.service;

import com.epam.cdp.service.dto.UserDto;

import java.util.Collection;

public interface UserService {

    void addUser(UserDto userDto);

    UserDto getUser(String userId);

    Collection<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);

    UserDto deleteUser(String userId);

}
