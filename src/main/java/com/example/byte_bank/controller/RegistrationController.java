package com.example.byte_bank.controller;

import com.example.byte_bank.form.UserRegistrationForm;
import com.example.byte_bank.service.UserService;
import com.example.byte_bank.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/register")
@AllArgsConstructor
public class RegistrationController {
    UserService userService;

    @PostMapping(path = "")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationForm userForm) {
        userService.registerUser(userForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> registerUser() {
        List<UserView> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
