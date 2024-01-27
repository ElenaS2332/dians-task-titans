package com.example.registrationservice;

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

        long id = (long) userRepository.findAll().size() + 1;

        User user = new User(id, username, password, name, surname);

        User savedUser = userRepository.save(user);

        return savedUser;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

//        if (optionalUser.isEmpty()) {
//            throw new InvalidUserExcepion(); // or handle as appropriate for your application
//        }

//        User user = optionalUser.get();
        return user;
    }

    @Override
    public Boolean userExist(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            return false;
        else {}
        return true;
    }

}

