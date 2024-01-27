package com.example.wineriesserviceapi.model;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException() {
        super("Invalid argument.");
    }

}
