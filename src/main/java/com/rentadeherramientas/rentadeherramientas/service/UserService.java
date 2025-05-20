package com.appsecurity.app_security.service;

import java.util.Optional;

import com.appsecurity.app_security.dto.SaveUser;
import com.appsecurity.app_security.persistence.entity.User;

public interface UserService {
    User registrOneCustomer(SaveUser newUser);

    Optional<User> findOneByUsername(String username);
}
