package com.yurtHomies.cies.model.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "student")
public class Student extends User{

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;


    // getters and setters
}
