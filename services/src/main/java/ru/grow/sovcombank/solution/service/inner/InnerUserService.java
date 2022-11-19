package ru.grow.sovcombank.solution.service.inner;

import ru.grow.sovcombank.solution.entity.user.UserEntity;

import java.util.stream.Stream;

/**
 * Внутренний сервис работы с сущностью {@link UserEntity}
 */
public interface InnerUserService {
    Stream<UserEntity> getAll();

    UserEntity getById(Long id);

    UserEntity save(UserEntity userEntity);

    UserEntity delete(Long id);

    UserEntity update(UserEntity userEntity);

    Stream<UserEntity> getUsersByValidStatus(Boolean status);

    Stream<UserEntity> getUsersByBlockStatus(Boolean status);
}
