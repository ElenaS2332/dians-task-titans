package com.example.demo.model;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException() {
        super("Invalid argument.");
    }

}
