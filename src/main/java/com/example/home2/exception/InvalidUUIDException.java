package com.example.home2.exception;

public class InvalidUUIDException extends RuntimeException {
    public InvalidUUIDException() {
        super("UUID is invalid");
    }
}
