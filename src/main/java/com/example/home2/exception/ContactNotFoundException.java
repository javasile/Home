package com.example.home2.exception;

public class ContactNotFoundException extends NoSuchFieldException {
    public ContactNotFoundException() {
        super("Contact not found, check id");
    }
}
