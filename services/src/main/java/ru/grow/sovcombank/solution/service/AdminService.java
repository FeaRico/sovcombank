package ru.grow.sovcombank.solution.service;

import ru.grow.sovcombank.solution.dto.user.UserPreviewDto;

import java.util.List;

public interface AdminService {
    List<UserPreviewDto> getAllUsers();

    List<UserPreviewDto> getUsersByValidStatus(Boolean status);

    List<UserPreviewDto> getUsersByBlockStatus(Boolean status);

    UserPreviewDto changeAccountBlockStatus(Long userId, Boolean status);

    UserPreviewDto changeAccountValidStatus(Long userId, Boolean status);
}
