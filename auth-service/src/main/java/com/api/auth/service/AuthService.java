package com.api.auth.service;

import com.api.auth.authorization.OperatorAuthenticationProvider;
import com.api.auth.authorization.UserAuthenticationProvider;
import com.api.auth.database.entity.Operator;
import com.api.auth.database.entity.OperatorToken;
import com.api.auth.database.entity.User;
import com.api.auth.database.repository.OperatorRepository;
import com.api.auth.database.repository.OperatorTokenRepository;
import com.api.auth.database.repository.UserRepository;
import com.api.auth.dto.ResponseDto;
import com.api.auth.util.JwtUtil;
import com.api.auth.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OperatorAuthenticationProvider operatorAuthenticationProvider;
    private final UserAuthenticationProvider userAuthenticationProvider;

    public ResponseDto validateOperatorToken(String username, String token) {
        Authentication authenticationRequest= new UsernamePasswordAuthenticationToken(username, token);

        Authentication authentication = operatorAuthenticationProvider.authenticate(authenticationRequest);

        return ResponseDto.success("success", authentication);
    }

    public ResponseDto validateUserLogin(String username, String password) {
        Authentication authenticationRequest= new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = userAuthenticationProvider.authenticate(authenticationRequest);

        return ResponseDto.success("success", authentication);
    }
}
