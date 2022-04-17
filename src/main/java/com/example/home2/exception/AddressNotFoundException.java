package com.example.home2.exception;

public class AddressNotFoundException extends NoSuchFieldException {
    public AddressNotFoundException() {
        super("Address not found, check id");
    }
}