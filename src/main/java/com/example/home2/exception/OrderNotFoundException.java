package com.example.home2.exception;

public class OrderNotFoundException extends NoSuchFieldException {
    public OrderNotFoundException() {
        super("Order not found, check id");
    }
}
