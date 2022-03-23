package com.example.restspring.exception;

public class BoardNotFoundException extends Exception {
    public BoardNotFoundException(String message) {
        super(message);
    }
}
