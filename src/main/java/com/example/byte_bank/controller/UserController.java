package com.example.byte_bank.controller;

import com.example.byte_bank.form.UserRegistrationForm;
import com.example.byte_bank.service.IUserService;
import com.example.byte_bank.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {

    IUserService userService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getAllUsers() {
        List<UserView> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/byid/{userId}")
    public UserView getUserByID(@PathVariable int userId) throws ChangeSetPersister.NotFoundException {
        return userService.getUserById(userId).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @GetMapping(path = "/byLogin/{userName}")
    public UserView getUserByUsername(@PathVariable String userName) throws ChangeSetPersister.NotFoundException {
        return userService.getUserByUsername(userName).orElseThrow(() -> {
                    return new UsernameNotFoundException("Can not found user with name " + userName);
                }
        );
    }

    @PatchMapping(path = "/changePassword/{userId}")
    public ResponseEntity<?> changePassword(@RequestBody String newPassword, @PathVariable int userId) {
        userService.changePassword(userId, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationForm userForm) {
        userService.registerUser(userForm);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
