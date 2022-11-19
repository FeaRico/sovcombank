package ru.grow.sovcombank.solution.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.dto.base.TimeObject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDto extends TimeObject {
    private String login;

    private String password;

    private PassportDto passport;
}
