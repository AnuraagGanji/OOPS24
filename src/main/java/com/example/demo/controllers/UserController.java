package com.example.demo.controllers;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDTO;
import com.example.demo.models.Users;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<Users> allUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        Users users = userRepository.findByEmail(loginDTO.getEmail());
        if(users == null) {
            return "User does not exist";
        }
        if(Objects.equals(users.getPassword(), loginDTO.getPassword())) {
            return "Login Successful";
        }
        else {
            return "Username/Password Incorrect";
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupDTO signupDTO) {
        Users users = userRepository.findByEmail(signupDTO.getEmail());
        if(users != null) {
            return "Forbidden, Account already exists";
        }
        Users newUsers = new Users(signupDTO.getEmail(), signupDTO.getName(), signupDTO.getPassword());
        userRepository.save(newUsers);
        return "Account Creation Successful";
    }

    @GetMapping("/user")
    public Object userDetail(@RequestParam("userID") Integer userId) {
        Optional<Users> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            return "User does not exist";
        }
        return user.get();
    }
}
