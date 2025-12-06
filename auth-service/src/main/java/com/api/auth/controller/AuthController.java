package com.api.auth.controller;

import com.api.auth.dto.ResponseDto;
import com.api.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/operator/validate")
    public ResponseEntity<ResponseDto> authenticateOperatorToken(
            @RequestParam String username,
            @RequestParam String token
    ){
        return authService.validateOperatorToken(username, token).getResponseEntity();
    }

    @GetMapping("/user/validate")
    public ResponseEntity<ResponseDto> authenticateUserLogin(
            @RequestParam String username,
            @RequestParam String password
    ){
        return authService.validateUserLogin(username, password).getResponseEntity();
    }
}
