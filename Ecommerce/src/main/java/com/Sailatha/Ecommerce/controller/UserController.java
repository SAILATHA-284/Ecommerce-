package com.Sailatha.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sailatha.Ecommerce.model.User;
import com.Sailatha.Ecommerce.security.JwtUtil;
import com.Sailatha.Ecommerce.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User loginRequest) {
       User user = userService.findByEmail(loginRequest.getEmail());
    if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        
        String token = jwtUtil.generateToken(user.getEmail());
        return "Bearer " + token;
    } else {
        return "Invalid email or password";
    }
    }
}

