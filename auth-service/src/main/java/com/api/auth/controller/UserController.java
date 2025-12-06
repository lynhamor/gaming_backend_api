package com.api.auth.controller;

import com.api.auth.dto.ResponseDto;
import com.api.auth.dto.UserDto;
import com.api.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(
            @RequestBody UserDto userDto
    ) throws Exception {
        return userService.register(userDto).getResponseEntity();
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getUser() {
        return userService.userList().getResponseEntity();
    }
}
