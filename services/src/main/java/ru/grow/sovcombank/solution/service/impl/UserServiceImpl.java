package ru.grow.sovcombank.solution.service.impl;

import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.dto.user.*;
import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;
import ru.grow.sovcombank.solution.entity.CurrencyEntity;
import ru.grow.sovcombank.solution.entity.user.UserEntity;
import ru.grow.sovcombank.solution.mapper.UserMapper;
import ru.grow.sovcombank.solution.service.AdminService;
import ru.grow.sovcombank.solution.service.SecurityUserService;
import ru.grow.sovcombank.solution.service.UserService;
import ru.grow.sovcombank.solution.service.inner.InnerCurrencyService;
import ru.grow.sovcombank.solution.service.inner.InnerUserService;
import ru.grow.sovcombank.solution.types.Role;
import ru.grow.sovcombank.solution.utils.SecurityUtils;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, AdminService {
    private final InnerUserService innerUserService;
    private final SecurityUserService securityService;
    private final InnerCurrencyService innerCurrencyService;
    private final UserMapper userMapper;

    public UserServiceImpl(InnerUserService innerUserService, SecurityUserService securityService, InnerCurrencyService innerCurrencyService, UserMapper userMapper) {
        this.innerUserService = innerUserService;
        this.securityService = securityService;
        this.innerCurrencyService = innerCurrencyService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto add(UserAddDto user) {
        UserEntity userEntity = userMapper.addDtoToServer(user);
        userEntity.setCreatedTime(new Date());
        userEntity.setRole(Role.USER);
        userEntity.setPassword(securityService.getPasswordEncoder().encode(user.getPassword()));
        BrokerAccountEntity brokerAccount = new BrokerAccountEntity();
        brokerAccount.setName("Стандартный счёт (Руб)");
        brokerAccount.setBalance(BigDecimal.ZERO);
        brokerAccount.setCreatedTime(new Date());
        CurrencyEntity rub = innerCurrencyService.getCurrencyByCode("RUB");
        if (rub == null) {
            rub = new CurrencyEntity();
            rub.setCreatedTime(new Date());
            rub.setCode("RUB");
            // TODO: 19.11.2022 Брать данные с внешнего сервера
            rub.setRate(BigDecimal.ZERO);
        }
        brokerAccount.setCurrency(rub);
        userEntity.addBrokerAccount(brokerAccount);
        return userMapper.toClient(innerUserService.save(userEntity));
    }

    // TODO: 19.11.2022 Заменить все эксепшены на кастомные
    @Override
    public UserDto delete(Long id, Principal principal) {
        if (!SecurityUtils.equalsId(id, principal))
            throw new IllegalStateException();
        return userMapper.toClient(innerUserService.delete(id));
    }

    // TODO: 19.11.2022 Можно сделать проверку через аспекты))
    @Override
    public UserDto update(UserInfoUpdateDto user, Principal principal) {
        if (!SecurityUtils.equalsId(user.getId(), principal))
            throw new IllegalStateException();
        UserEntity entity = userMapper.infoEditDtoToServer(user);
        return userMapper.toClient(innerUserService.update(entity));
    }

    @Override
    public UserDto updatePassword(UserPasswordUpdateDto user, Principal principal) {
        if (!SecurityUtils.equalsId(user.getId(), principal))
            throw new IllegalStateException();

        UserEntity entity = innerUserService.getById(user.getId());

        if (entity.getPassword().equals(securityService.getPasswordEncoder().encode(user.getOldPassword()))) {
            entity.setPassword(securityService.getPasswordEncoder().encode(user.getNewPassword()));
        }
        return userMapper.toClient(innerUserService.update(entity));
    }

    @Override
    public List<UserPreviewDto> getAllUsers() {
        return innerUserService.getAll()
                .map(userMapper::serverToPreviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserPreviewDto> getUsersByValidStatus(Boolean status) {
        return innerUserService.getUsersByValidStatus(status)
                .map(userMapper::serverToPreviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserPreviewDto> getUsersByBlockStatus(Boolean status) {
        return innerUserService.getUsersByBlockStatus(status)
                .map(userMapper::serverToPreviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserPreviewDto changeAccountBlockStatus(Long userId, Boolean status) {
        UserEntity entity = innerUserService.getById(userId);
        entity.setIsBlocked(status);
        return userMapper.serverToPreviewDto(innerUserService.update(entity));
    }

    @Override
    public UserPreviewDto changeAccountValidStatus(Long userId, Boolean status) {
        UserEntity entity = innerUserService.getById(userId);
        entity.setIsValidated(status);
        return userMapper.serverToPreviewDto(innerUserService.update(entity));
    }
}
