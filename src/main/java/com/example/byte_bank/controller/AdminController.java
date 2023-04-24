package com.example.byte_bank.controller;

import com.example.byte_bank.service.AdminService;
import com.example.byte_bank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
@AllArgsConstructor
public class AdminController {

    AdminService adminService;

    @PostMapping(path = "/deleteById/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
        adminService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/deleteByUsername/{userName}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable String userName) {
        adminService.deleteUserByUsername(userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/deleteAllUsers")
    public ResponseEntity<?> deleteAllUsers() {
        adminService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "/creditBytesById/{userId}")
    public ResponseEntity<?> creditToUserById(@RequestBody int bytesNumber, @PathVariable int userId) {
        adminService.creditToUserById(bytesNumber, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "/creditBytesByUsername/{userName}")
    public ResponseEntity<?> creditToUserByUsername(@RequestBody int bytesNumber, @PathVariable String userName) {
        adminService.creditToUserByUsername(bytesNumber, userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "/grantAdminRole/{userid}")
    public void grantAdminRole(@PathVariable int userId) {
        adminService.grantAdmineRole(userId);
    }


}
