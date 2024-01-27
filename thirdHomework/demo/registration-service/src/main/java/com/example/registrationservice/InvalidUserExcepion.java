package com.example.registrationservice;

public class InvalidUserExcepion  extends  RuntimeException{
    public InvalidUserExcepion() {
        super("Invalid user credentials");
    }

}
