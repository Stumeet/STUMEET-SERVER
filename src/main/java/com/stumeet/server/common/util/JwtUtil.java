package com.stumeet.server.common.util;

public class JwtUtil {

    private JwtUtil() {

    }

    public static String resolveToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

}
