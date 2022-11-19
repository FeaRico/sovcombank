package ru.grow.sovcombank.solution.service;

import ru.grow.sovcombank.solution.dto.user.UserAddDto;
import ru.grow.sovcombank.solution.dto.user.UserDto;
import ru.grow.sovcombank.solution.dto.user.UserInfoUpdateDto;
import ru.grow.sovcombank.solution.dto.user.UserPasswordUpdateDto;

import java.security.Principal;

public interface UserService {
    UserDto add(UserAddDto user);

    UserDto delete(Long id, Principal principal);

    UserDto update(UserInfoUpdateDto user, Principal principal);

    UserDto updatePassword(UserPasswordUpdateDto user, Principal principal);


}
