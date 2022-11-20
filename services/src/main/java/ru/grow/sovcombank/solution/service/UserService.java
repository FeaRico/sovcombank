package ru.grow.sovcombank.solution.service;

import ru.grow.sovcombank.solution.dto.user.UserAddDto;
import ru.grow.sovcombank.solution.dto.user.UserDto;
import ru.grow.sovcombank.solution.dto.user.UserInfoUpdateDto;
import ru.grow.sovcombank.solution.dto.user.UserPasswordUpdateDto;

public interface UserService {
    UserDto add(UserAddDto user);

    UserDto delete();

    UserDto update(UserInfoUpdateDto user);

    UserDto updatePassword(UserPasswordUpdateDto user);
}
