package com.example.wineriesservices;

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

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "address1", column = @Column(name = "user_address1")),
//            @AttributeOverride(name = "address2", column = @Column(name = "user_address2"))
//    })
//    private UserAddress userAddress;
    public User() {}

    public User(Long id, String username, String password, String name, String surname) {
        this.Id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}



