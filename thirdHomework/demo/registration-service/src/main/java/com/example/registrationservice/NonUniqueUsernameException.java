package com.example.registrationservice;

public class NonUniqueUsernameException extends RuntimeException{
    public NonUniqueUsernameException(){
        super("Username already in use.");
    }
}
