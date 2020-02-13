package com.jean.leal.ui.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@CrossOrigin("*")
public interface UsersController {

    @GetMapping("/status/check")
    String status();
}
