package com.example.demo.model;

public class NonUniqueUsernameException extends RuntimeException{
    public NonUniqueUsernameException(){
        super("Username already in use.");
    }
}
