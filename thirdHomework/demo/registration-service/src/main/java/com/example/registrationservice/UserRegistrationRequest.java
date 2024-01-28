package com.example.registrationservice;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String username;
    private String password;
    private String repeatPassword;
    private String name;
    private String surname;
}
