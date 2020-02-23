package com.jean.leal.ui.controllers;

import com.jean.leal.ui.model.request.CreateUserRequestModel;
import com.jean.leal.ui.model.response.CreateUserResponseModel;
import com.jean.leal.ui.model.response.UserResponseModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/users")
@CrossOrigin("*")
public interface UsersController {

    @GetMapping("/status/check")
    String status();

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel createUserRequestModel);

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId);
}
