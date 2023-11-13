package com.arbc.development.mvc.services;

import java.util.List;
import java.util.Optional;

import com.arbc.development.mvc.models.dto.UserDto;
import com.arbc.development.mvc.models.entities.User;
import com.arbc.development.mvc.models.entities.UserRequest;

public interface UserService {
    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(User user);

    Optional<UserDto> update(UserRequest user, Long id);

    void remove(Long id);
}
