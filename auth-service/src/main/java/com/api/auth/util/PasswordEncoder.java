package com.api.auth.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    private static final BCryptPasswordEncoder.BCryptVersion VERSION = BCryptPasswordEncoder.BCryptVersion.$2A;

    public static String encode(String rawPassword) {

        return new BCryptPasswordEncoder(VERSION, 4).encode(rawPassword);
    }

    public static String encodeAgentPassword(String rawPassword, String passwordIV) {

        return new BCryptPasswordEncoder(VERSION, 4).encode(String.format("%s%s", rawPassword, passwordIV));
    }

    public static boolean matches(String rawPassword, String encodedPassword) {

        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    public static boolean matchesAgentPassword(String rawPassword, String passwordIV,  String hashedPassword) {

        String combined = String.format("%s%s", rawPassword, passwordIV);
        return new BCryptPasswordEncoder().matches(combined, hashedPassword);
    }

    public static String generatePasswordIV() {

        return KeyGeneratorUtil.generate(16);
    }
}
