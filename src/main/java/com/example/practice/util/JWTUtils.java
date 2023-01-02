package com.example.practice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.example.practice.user.exception.PasswordNotMatchException;

public class JWTUtils {

    private static final String KEY = "fastcampus";

    public static String getIssuer(String token) {

        String issuer = JWT.require(Algorithm.HMAC512(KEY.getBytes()))
                .build()
                .verify(token)
                .getIssuer();

        return issuer;
    }
}
