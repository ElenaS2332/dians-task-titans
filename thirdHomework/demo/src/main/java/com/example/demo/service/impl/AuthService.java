package com.example.demo.service.impl;

import com.example.demo.model.InvalidArgumentsException;
import com.example.demo.model.InvalidUserExcepion;
import com.example.demo.model.PasswordsDoNotMatchException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements AuthServiceе {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean credentialsInvalid(String username, String password) {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }

    @Override
    public User login(String username, String password) {
        if (credentialsInvalid(username, password)) {
            throw new InvalidArgumentsException();
        }

        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserExcepion::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (credentialsInvalid(username, password)) {
            throw new InvalidArgumentsException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        User user = new User(username, password, name, surname);
        // Assuming User class has a constructor that takes username, password, name, and surname.

        User savedUser = userRepository.save(user);
        return (com.example.demo.model.User) savedUser;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
