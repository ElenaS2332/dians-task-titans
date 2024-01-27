package com.example.registrationservice;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException() {
        super("Invalid argument.");
    }

}
