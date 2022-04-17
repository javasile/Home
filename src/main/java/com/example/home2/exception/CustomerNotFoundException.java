package com.example.home2.exception;

public class CustomerNotFoundException extends NoSuchFieldException {
    public CustomerNotFoundException() {
        super("Customer not found, check id");
    }
}