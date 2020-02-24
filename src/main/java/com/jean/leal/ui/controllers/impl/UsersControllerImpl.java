package com.jean.leal.ui.controllers.impl;

import com.jean.leal.services.UserService;
import com.jean.leal.shared.UserDto;
import com.jean.leal.ui.controllers.UsersController;
import com.jean.leal.ui.model.request.CreateUserRequestModel;
import com.jean.leal.ui.model.response.CreateUserResponseModel;
import com.jean.leal.ui.model.response.UserResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersControllerImpl implements UsersController {

    private final Environment environment;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersControllerImpl(Environment environment, UserService userService, ModelMapper modelMapper) {
        this.environment = environment;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String status() {
        return "Working on port " + environment.getProperty("local.server.port") + ", with token "
                + environment.getProperty("token.secret");
    }

    @Override
    public ResponseEntity<CreateUserResponseModel> createUser(CreateUserRequestModel createUserRequestModel) {
        UserDto userDto = modelMapper.map(createUserRequestModel, UserDto.class);
        userDto = userService.createUser(userDto);

        CreateUserResponseModel responseModel = modelMapper.map(userDto, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

    @Override
    public ResponseEntity<UserResponseModel> getUser(String userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        UserResponseModel returnValue = modelMapper.map(userDto, UserResponseModel.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
