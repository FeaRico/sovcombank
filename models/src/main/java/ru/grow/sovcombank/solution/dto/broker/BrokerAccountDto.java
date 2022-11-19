package ru.grow.sovcombank.solution.dto.broker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.dto.CurrencyDto;
import ru.grow.sovcombank.solution.dto.base.TimeObject;
import ru.grow.sovcombank.solution.dto.user.UserDto;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrokerAccountDto extends TimeObject {
    /**
     * Имя счёта
     */
    private String accountName;

    /**
     * Пользователь к которому привязан счёт
     */
    private UserDto userDto;

    /**
     * Валюта
     */
    private CurrencyDto currency;

    /**
     * Баланс счёта
     */
    private BigDecimal balance;
}
