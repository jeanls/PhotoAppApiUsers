package com.jean.leal.services;

import com.jean.leal.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserDetailsByEmail(String email);

    UserDto getUserByUserId(String userId);
}
