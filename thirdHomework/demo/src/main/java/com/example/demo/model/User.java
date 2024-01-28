package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dians-users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;

    @Convert(converter = UserFullnameConverter.class)
    private UserFullname fullname;

    public User() {}

    public User(Long id, String username, String password, String name, String surname) {
        this.Id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}



