package com.yurtHomies.cies.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    private Long id;

    @Column(name = "can_modify_questions")
    private Boolean canModifyQuestions = false;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    // getters and setters
}

