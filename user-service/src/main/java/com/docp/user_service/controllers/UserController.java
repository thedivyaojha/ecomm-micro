package com.docp.user_service.controllers;

import com.docp.user_service.dto.UserRequest;
import com.docp.user_service.dto.UserResponse;
import com.docp.user_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return userResponse;
    }

    @GetMapping("/{id}")
    public UserResponse findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/health")
    public String getHealth(){
        return "I'm Ok";
    }


}
