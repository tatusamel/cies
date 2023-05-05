package com.yurtHomies.cies.model.requests;

import lombok.Data;

@Data
public class UserRegisterRequest {
    String firstName;
    String lastName;
    Long id;
    String email;
}
