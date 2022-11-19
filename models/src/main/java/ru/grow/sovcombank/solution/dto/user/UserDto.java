package ru.grow.sovcombank.solution.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.dto.base.TimeObject;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountDto;
import ru.grow.sovcombank.solution.types.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends TimeObject {
    /**
     * Логин
     */
    private String login;

    /**
     * Пароль (???)
     */
    private String password;

    /**
     * Паспорт пользователя
     */
    private PassportDto passport;

    // TODO: 19.11.2022 Убрать юзера в счёт
    /**
     * Брокерский счёт
     */
    private List<BrokerAccountDto> brokerAccounts;

    /**
     * Статус блокировки
     */
    private Boolean isBlocked;

    /**
     * Статус валидации
     */
    private Boolean isValidated;

    /**
     * Роль пользователя
     */
    private Role role;
}
