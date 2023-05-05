package com.yurtHomies.cies.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.usertype.UserType;


@Entity
@Data
public class User {


    @Id
    private Long id;

    private String password;

    private String firstName;

    private String lastName;

    private String email;


    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String profilePicture;

    private boolean isEnabled = false;

    public enum UserType {
        ADMIN,
        STUDENT,
        DEPARTMENT_MANAGER,
        INSTRUCTOR
    }

    // Getters and Setters
    // ...

}
