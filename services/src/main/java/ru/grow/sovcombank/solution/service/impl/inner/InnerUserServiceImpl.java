package ru.grow.sovcombank.solution.service.impl.inner;

import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.entity.user.UserEntity;
import ru.grow.sovcombank.solution.repository.UserEntityRepository;
import ru.grow.sovcombank.solution.service.inner.InnerUserService;

import java.util.stream.Stream;

@Service
public class InnerUserServiceImpl implements InnerUserService {
    private final UserEntityRepository userRepository;

    public InnerUserServiceImpl(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Stream<UserEntity> getAll() {
        return userRepository.findAll().stream();
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        if (userEntity.getId() != null)
            throw new IllegalStateException();
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity delete(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        userRepository.delete(entity);
        return entity;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        if (userEntity.getId() == null)
            throw new IllegalStateException();
        userRepository.findById(userEntity.getId())
                .orElseThrow(IllegalArgumentException::new);
        return userRepository.save(userEntity);
    }

    @Override
    public Stream<UserEntity> getUsersByValidStatus(Boolean status) {
        return userRepository.getUsersByValidStatus(status);
    }

    @Override
    public Stream<UserEntity> getUsersByBlockStatus(Boolean status) {
        return userRepository.getUsersByBlockStatus(status);
    }
}
