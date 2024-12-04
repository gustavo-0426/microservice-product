package com.co.softworld.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String authorities;
}
