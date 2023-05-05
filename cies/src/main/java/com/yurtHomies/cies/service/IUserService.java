package com.yurtHomies.cies.service;


import com.yurtHomies.cies.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getUsers();

    Optional<User> findById(Long id);

}
