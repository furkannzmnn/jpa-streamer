package com.example.jpastreamer;

import javax.persistence.*;
import lombok.Data;

import java.util.function.Predicate;

@Data
@Entity
@Table(name = "users", schema = "public")
public class User {

    public static final Predicate<User> NAME = user -> user.getName().equals("John");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
}
