package ru.grow.sovcombank.solution.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.dto.base.TimeObject;
import ru.grow.sovcombank.solution.types.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends TimeObject {
    /**
     * Логин
     */
    private String username;

    /**
     * Пароль (???)
     */
    private String password;

    /**
     * Паспорт пользователя
     */
    private PassportDto passport;

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
