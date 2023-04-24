package com.example.byte_bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
    @GetMapping(path = "/")
    public ResponseEntity<?> redirector() {

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
