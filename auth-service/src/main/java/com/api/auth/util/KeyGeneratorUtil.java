package com.api.auth.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class KeyGeneratorUtil {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generate(int byteLength){
        byte[] bytes = new byte[byteLength];
        secureRandom.nextBytes(bytes);

        StringBuilder sb = new StringBuilder(byteLength * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
