package com.yurtHomies.cies.controller;

import com.yurtHomies.cies.model.entities.User;
import com.yurtHomies.cies.model.requests.ForgotPasswordRequest;
import com.yurtHomies.cies.model.requests.UserRegisterRequest;
import com.yurtHomies.cies.model.requests.UserRequest;
import com.yurtHomies.cies.service.EmailSenderService;
import com.yurtHomies.cies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final EmailSenderService emailSenderService;

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
        user.setEmail(request.getEmail());

        String thePassword = UUID.randomUUID().toString();
        user.setPassword(thePassword);
        userService.saveUser(user);

        emailSenderService.sendEmail(user.getEmail(),
                               "Welcome to ICES4HU.",
                                      "Welcome to ICES4HU. Here is your password: " + thePassword
                                    + "\n\nYou can always change your password later on.");
        return new String("Registration is successful. Your password has been sent to your email.");
    }

    @PostMapping("forgotPassword")
    public String forgotPassword(@RequestBody ForgotPasswordRequest request) {
        User user = userService.getUserById(request.getId());
        if ( user == null ) {
            return new String("No such user with id: " + String.valueOf(request.getId()));
        }
        emailSenderService.sendEmail(user.getEmail(),
                "Your ICES4HU password",
                "Your ICES4HU password is: " + user.getPassword());
        return new String("Your password has been sent to the email which is associated with the id: " + user.getId());
    }

    public static int generateRandomNumber(int mn, int mx){
        Random rand = new Random();
        return rand.nextInt((mx-mn)+1)+ mn;
    }



}
