package ru.grow.sovcombank.solution.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.dto.base.TimeObject;

/**
 * Трансферный объект пользователей с информацией для администраторов
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPreviewDto extends TimeObject {
    private PassportDto passport;

    private Boolean isBlocked;

    private Boolean isValidated;
}
