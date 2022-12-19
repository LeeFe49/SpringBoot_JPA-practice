package com.example.practice.user.exception;

public class ExistsEmailException extends RuntimeException {
    public ExistsEmailException(String s) {
        super(s);
    }
}
