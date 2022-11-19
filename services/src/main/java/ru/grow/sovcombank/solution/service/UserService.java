package ru.grow.sovcombank.solution.service;

import ru.grow.sovcombank.solution.dto.user.UserAddDto;
import ru.grow.sovcombank.solution.dto.user.UserDto;
import ru.grow.sovcombank.solution.dto.user.UserUpdateDto;

public interface UserService {
    UserDto add(UserAddDto user);

    UserDto delete(Long id);

    UserDto update(UserUpdateDto user);
}
