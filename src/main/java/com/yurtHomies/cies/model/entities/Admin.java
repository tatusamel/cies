package com.yurtHomies.cies.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    // getters and setters
}
