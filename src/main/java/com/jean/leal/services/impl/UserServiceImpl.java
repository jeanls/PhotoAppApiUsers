package com.jean.leal.services.impl;

import com.jean.leal.data.UserEntity;
import com.jean.leal.repositories.UserRepository;
import com.jean.leal.services.UserService;
import com.jean.leal.shared.UserDto;
import com.jean.leal.ui.model.response.AlbumResponseModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    final ModelMapper modelMapper;
    final RestTemplate restTemplate;
    final Environment environment;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
        return userDto;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (Objects.isNull(userEntity)) {
            throw new UsernameNotFoundException(email);
        }
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        ResponseEntity<List<AlbumResponseModel>> responseEntity =
                restTemplate.exchange(String.format(Objects.requireNonNull(environment.getProperty("albums.url")), userId), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<AlbumResponseModel>>() {});
        List<AlbumResponseModel> albums = responseEntity.getBody();
        userDto.setAlbums(albums);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(s);
        if (Objects.isNull(userEntity)) {
            throw new UsernameNotFoundException(s);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
                true, true, true, true, new ArrayList<>());
    }
}
