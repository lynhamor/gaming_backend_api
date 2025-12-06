package com.api.auth.authorization;

import com.api.auth.database.entity.Operator;
import com.api.auth.database.entity.User;
import com.api.auth.database.repository.UserRepository;
import com.api.auth.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    public final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        String password = authentication.getCredentials().toString();

        if (username == null || password == null) {
            throw new BadCredentialsException("Missing Basic credentials");
        }

        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null)
            throw new BadCredentialsException("user not found");

        if (!isValid(user, password)) {
            throw new BadCredentialsException("Invalid agent credentials");
        }

        UserAdapter userAdapter = new UserAdapter(user);

        return new UsernamePasswordAuthenticationToken(userAdapter, password, userAdapter.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean isValid(User user, String rawPassword) {

        return user != null && PasswordEncoder.matchesUserPassword(rawPassword, user.getPasswordIv(), user.getPassword());
    }
}
