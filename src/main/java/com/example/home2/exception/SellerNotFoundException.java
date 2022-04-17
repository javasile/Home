package com.example.home2.exception;

public class SellerNotFoundException extends NoSuchFieldException {
    public SellerNotFoundException() {
        super("Seller not found, check id");
    }
}