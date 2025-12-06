package com.api.auth.authorization;

import com.api.auth.database.entity.Operator;
import com.api.auth.database.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperatorAuthenticationProvider implements AuthenticationProvider {

    private final OperatorRepository operatorRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String operatorName = authentication.getName();

        String token = authentication.getCredentials().toString();

        if (operatorName == null || token == null) {
            throw new BadCredentialsException("Missing Basic credentials");
        }

        if (!isValid(operatorName, token)) {
            throw new BadCredentialsException("Invalid agent credentials");
        }

        Operator operator = operatorRepository.findByOperatorName(operatorName).orElse(null);
        OperatorAdapter operatorAdapter = new OperatorAdapter(operator);

        return new UsernamePasswordAuthenticationToken(operatorAdapter, token, operatorAdapter.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean isValid(String operatorName, String token) {
        return operatorRepository.isValidOperatorNameAndToken(operatorName, token);
    }
}
