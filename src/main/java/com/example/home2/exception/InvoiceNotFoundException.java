package com.example.home2.exception;

public class InvoiceNotFoundException extends NoSuchFieldException{
    public InvoiceNotFoundException() {
        super("Invoice not found check id!");
    }
}
