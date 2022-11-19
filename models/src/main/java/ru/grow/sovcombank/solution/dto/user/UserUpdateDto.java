package ru.grow.sovcombank.solution.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.dto.base.TimeObject;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountUpdateDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto extends TimeObject {
    private String login;

    private String password;

    private PassportDto passport;

    private BrokerAccountUpdateDto brokerAccount;
}
