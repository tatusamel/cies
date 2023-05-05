package com.yurtHomies.cies.controller;

import com.yurtHomies.cies.model.entities.User;
import com.yurtHomies.cies.model.requests.ForgotPasswordRequest;
import com.yurtHomies.cies.model.requests.UserRegisterRequest;
import com.yurtHomies.cies.model.requests.UserRequest;
import com.yurtHomies.cies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserRequest request) {

        User user = userService.getUserById(request.getId());
        // user check
        if ( user == null ) {
            return new String("No such user");
        }
        // password check
        if ( user.getPassword().equals(request.getPassword()) ) {
            return new String("Login successful");
        }
        return new String("Either password or id do not match.");
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterRequest request) {
        if (userService.findById(request.getId()).isPresent()) {
            return new String("Username is already in use");
        }
        User user = new User();
        user.setId(request.getId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        // TODO:: sifreyi burada rastgele uret
        user.setPassword(String.valueOf(generateRandomNumber(100000,999999)));
        userService.saveUser(user);

        return new String("Successful");
    }

    @PostMapping("forgotPassword")
    public String forgotPassword(@RequestBody ForgotPasswordRequest request) {
        User user = userService.getUserById(request.getId());
        if ( user == null ) {
            return new String("No such user with id: " + String.valueOf(request.getId()));
        }
        return new String(user.getPassword());
    }

    public static int generateRandomNumber(int mn, int mx){
        Random rand = new Random();
        return rand.nextInt((mx-mn)+1)+ mn;
    }



}
