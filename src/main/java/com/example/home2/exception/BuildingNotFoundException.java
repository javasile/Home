package com.example.home2.exception;

public class BuildingNotFoundException extends NoSuchFieldException {
    public BuildingNotFoundException() {
        super("Building not found, check id");
    }
}