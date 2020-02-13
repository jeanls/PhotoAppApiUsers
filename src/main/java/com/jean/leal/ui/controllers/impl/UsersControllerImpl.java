package com.jean.leal.ui.controllers.impl;

import com.jean.leal.ui.controllers.UsersController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersControllerImpl implements UsersController {
    @Override
    public String status() {
        return "Working";
    }
}
