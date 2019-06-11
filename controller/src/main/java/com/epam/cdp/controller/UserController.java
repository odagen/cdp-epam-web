package com.epam.cdp.controller;

import com.epam.cdp.service.UserService;
import com.epam.cdp.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_XML_VALUE;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces =
            {APPLICATION_JSON_VALUE, TEXT_XML_VALUE})
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") @NotBlank String userId) {
        UserDto userDto = userService.getUser(userId);

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Collection<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = {APPLICATION_JSON_VALUE, TEXT_XML_VALUE})
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        userService.addUser(userDto);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> removeUser(@PathVariable("userId") @NotBlank String userId) {
        UserDto userDto = userService.deleteUser(userId);

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
