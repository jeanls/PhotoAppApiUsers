package com.jean.leal.repositories;

import com.jean.leal.data.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);
}
