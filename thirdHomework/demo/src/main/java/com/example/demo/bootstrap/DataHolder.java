package com.example.demo.bootstrap;

import com.example.demo.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component


public class DataHolder {
    public static List<User> users = null;
    private final UserRepository userRepository;


    public DataHolder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
