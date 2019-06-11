package com.epam.cdp.service;

import com.epam.cdp.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final Map<String, UserDto> userMap = new HashMap<>();

    @Override
    public void addUser(UserDto userDto) {
        userMap.put(userDto.getUserId(), userDto);
    }

    @Override
    public UserDto getUser(String userId) {
        return userMap.get(userId);
    }

    @Override
    public Collection<UserDto> getAllUsers() {
        return userMap.values();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return userMap.put(userDto.getUserId(), userDto);
    }

    @Override
    public UserDto deleteUser(String userId) {
        return userMap.remove(userId);
    }
}
