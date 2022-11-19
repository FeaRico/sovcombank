package ru.grow.sovcombank.solution.service.impl;

import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.dto.user.UserAddDto;
import ru.grow.sovcombank.solution.dto.user.UserDto;
import ru.grow.sovcombank.solution.dto.user.UserInfoUpdateDto;
import ru.grow.sovcombank.solution.dto.user.UserPasswordUpdateDto;
import ru.grow.sovcombank.solution.entity.user.UserEntity;
import ru.grow.sovcombank.solution.mapper.UserMapper;
import ru.grow.sovcombank.solution.repository.UserEntityRepository;
import ru.grow.sovcombank.solution.service.SecurityUserService;
import ru.grow.sovcombank.solution.service.UserService;
import ru.grow.sovcombank.solution.types.Role;
import ru.grow.sovcombank.solution.utils.SecurityUtils;

import java.security.Principal;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private final UserEntityRepository userRepository;
    private final SecurityUserService securityService;
    private final UserMapper userMapper;

    public UserServiceImpl(UserEntityRepository userRepository, SecurityUserService securityService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto add(UserAddDto user) {
        UserEntity userEntity = userMapper.addDtoToServer(user);
        userEntity.setCreatedTime(new Date());
        userEntity.setRole(Role.USER);
        userEntity.setPassword(securityService.getPasswordEncoder().encode(user.getPassword()));
        return userMapper.toClient(userRepository.save(userEntity));
    }

    // TODO: 19.11.2022 Заменить все эксепшены на кастомные
    @Override
    public UserDto delete(Long id, Principal principal) {
        if (!SecurityUtils.equalsId(id, principal))
            throw new IllegalStateException();
        return userMapper.toClient(userRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    // TODO: 19.11.2022 Можно сделать проверку через аспекты))
    @Override
    public UserDto update(UserInfoUpdateDto user, Principal principal) {
        if (!SecurityUtils.equalsId(user.getId(), principal))
            throw new IllegalStateException();
        userRepository.findById(user.getId())
                .orElseThrow(IllegalArgumentException::new);
        UserEntity entity = userMapper.infoEditDtoToServer(user);
        return userMapper.toClient(userRepository.save(entity));
    }

    @Override
    public UserDto updatePassword(UserPasswordUpdateDto user, Principal principal) {
        if (!SecurityUtils.equalsId(user.getId(), principal))
            throw new IllegalStateException();

        UserEntity entity = userRepository.findById(user.getId())
                .orElseThrow(IllegalArgumentException::new);

        if (entity.getPassword().equals(securityService.getPasswordEncoder().encode(user.getOldPassword()))) {
            entity.setPassword(securityService.getPasswordEncoder().encode(user.getNewPassword()));
        }
        return userMapper.toClient(userRepository.save(entity));
    }
}
