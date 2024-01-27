package com.example.registrationservice;

import java.util.List;

public interface AuthServiceе {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname);
    List<User> findAll();
    User getUserByUsername(String username);
    Boolean userExist(String username);
}
