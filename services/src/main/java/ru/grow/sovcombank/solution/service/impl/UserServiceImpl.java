package ru.grow.sovcombank.solution.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grow.sovcombank.solution.dto.user.*;
import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;
import ru.grow.sovcombank.solution.entity.CurrencyEntity;
import ru.grow.sovcombank.solution.entity.user.SecurityUserEntity;
import ru.grow.sovcombank.solution.entity.user.UserEntity;
import ru.grow.sovcombank.solution.mapper.UserMapper;
import ru.grow.sovcombank.solution.service.AdminService;
import ru.grow.sovcombank.solution.service.CurrencyService;
import ru.grow.sovcombank.solution.service.SecurityUserService;
import ru.grow.sovcombank.solution.service.UserService;
import ru.grow.sovcombank.solution.service.inner.InnerUserService;
import ru.grow.sovcombank.solution.types.Role;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService, AdminService {
    private final InnerUserService innerUserService;
    private final SecurityUserService securityService;
    private final CurrencyService currencyService;
    private final UserMapper userMapper;

    public UserServiceImpl(InnerUserService innerUserService, SecurityUserService securityService, CurrencyService currencyService, UserMapper userMapper) {
        this.innerUserService = innerUserService;
        this.securityService = securityService;
        this.currencyService = currencyService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto add(UserAddDto user) {
        UserEntity userEntity = userMapper.addDtoToServer(user);
        userEntity.setCreatedTime(new Date());
        userEntity.setRole(Role.ROLE_USER);
        userEntity.setPassword(securityService.getPasswordEncoder().encode(user.getPassword()));
        userEntity.setIsBlocked(false);
        userEntity.setIsValidated(false);
        userEntity.getPassportEntity().setCreatedTime(new Date());

        BrokerAccountEntity brokerAccount = new BrokerAccountEntity();
        brokerAccount.setName("Стандартный счёт (Руб)");
        brokerAccount.setBalance(BigDecimal.ZERO);
        brokerAccount.setCreatedTime(new Date());

        CurrencyEntity rub = currencyService.getCurrencyByCode("RUB");
        brokerAccount.setCurrency(rub);
        userEntity.addBrokerAccount(brokerAccount);
        return userMapper.toClient(innerUserService.save(userEntity));
    }

    // TODO: 19.11.2022 Заменить все эксепшены на кастомные
    @Override
    public UserDto delete() {
        SecurityUserEntity securityUser = (SecurityUserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return userMapper.toClient(innerUserService.delete(securityUser.getId()));
    }

    @Override
    public UserDto update(UserInfoUpdateDto user) {
        SecurityUserEntity securityUser = (SecurityUserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        UserEntity userEntity = innerUserService.getById(securityUser.getId());
        UserEntity entity = userMapper.infoEditDtoToServer(user);
        userEntity.setUsername(entity.getUsername());
        userEntity.setPassportEntity(entity.getPassportEntity());
        userEntity.setChangedTime(new Date());

        return userMapper.toClient(innerUserService.update(userEntity));
    }

    @Override
    public UserDto updatePassword(UserPasswordUpdateDto user) {
        SecurityUserEntity securityUser = (SecurityUserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        UserEntity entity = innerUserService.getById(securityUser.getId());
        entity.setChangedTime(new Date());

        if (securityService.getPasswordEncoder().matches(user.getOldPassword(), entity.getPassword())) {
            entity.setPassword(securityService.getPasswordEncoder().encode(user.getNewPassword()));
        }
        return userMapper.toClient(innerUserService.update(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPreviewDto> getAllUsers() {
        return innerUserService.getAll()
                .map(userMapper::serverToPreviewDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPreviewDto> getUsersByValidStatus(Boolean status) {
        return innerUserService.getUsersByValidStatus(status)
                .map(userMapper::serverToPreviewDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPreviewDto> getUsersByBlockStatus(Boolean status) {
        return innerUserService.getUsersByBlockStatus(status)
                .map(userMapper::serverToPreviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserPreviewDto changeAccountBlockStatus(Long userId, Boolean status) {
        UserEntity entity = innerUserService.getById(userId);
        entity.setIsBlocked(status);
        entity.setChangedTime(new Date());
        return userMapper.serverToPreviewDto(innerUserService.update(entity));
    }

    @Override
    public UserPreviewDto changeAccountValidStatus(Long userId, Boolean status) {
        UserEntity entity = innerUserService.getById(userId);
        entity.setIsValidated(status);
        entity.setChangedTime(new Date());
        return userMapper.serverToPreviewDto(innerUserService.update(entity));
    }
}
