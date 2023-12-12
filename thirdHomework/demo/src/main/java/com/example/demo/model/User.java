package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "diansusers")
public class User {

    @Id
    private String username;

    private String password;
    private String name;
    private String surname;

    @Convert(converter = UserFullnameConverter.class)
    private UserFullname fullname;

//    @OneToMany(fetch = FetchType.EAGER)
    // private List<ShoppingCart> carts;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address1", column = @Column(name = "user_address1")),
            @AttributeOverride(name = "address2", column = @Column(name = "user_address2"))
    })
    private UserAddress userAddress;

    // Empty constructor for JPA
    public User() {
    }

    // Constructor for basic information
    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    // Constructor for additional information
    public User(String username, String password, String name, String surname, UserAddress userAddress) {
        this.username = username;
        this.password = password;
        this.fullname = new UserFullname(name, surname);
        this.userAddress = userAddress;
    }

    // Getters and setters...
}



