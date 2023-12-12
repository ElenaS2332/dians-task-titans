package com.example.demo.model;

public class InvalidUserExcepion  extends  RuntimeException{
    public InvalidUserExcepion() {
        super("Invalid user credentials");
    }

}
