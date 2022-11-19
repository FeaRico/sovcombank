package ru.grow.sovcombank.solution.dto.broker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.dto.CurrencyDto;
import ru.grow.sovcombank.solution.dto.FinanceTransactionDto;
import ru.grow.sovcombank.solution.dto.base.TimeObject;

import java.math.BigDecimal;
import java.util.List;

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
     * Валюта
     */
    private CurrencyDto currency;

    /**
     * Баланс счёта
     */
    private BigDecimal balance;

    /**
     * Транзакции счёта
     */
    private List<FinanceTransactionDto> transactions;
}
