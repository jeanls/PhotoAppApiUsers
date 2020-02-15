package com.jean.leal.ui.controllers.impl;

import com.jean.leal.services.UserService;
import com.jean.leal.shared.UserDto;
import com.jean.leal.ui.controllers.UsersController;
import com.jean.leal.ui.model.request.CreateUserRequestModel;
import com.jean.leal.ui.model.response.CreateUserResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersControllerImpl implements UsersController {

    @Autowired
    private Environment environment;

    @Autowired
    UserService userService;

    @Override
    public String status() {
        return "Working on port " + environment.getProperty("local.server.port");
    }

    @Override
    public ResponseEntity<CreateUserResponseModel> createUser(CreateUserRequestModel createUserRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(createUserRequestModel, UserDto.class);
        userDto = userService.createUser(userDto);

        CreateUserResponseModel responseModel = modelMapper.map(userDto, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }
}
