package ru.grow.sovcombank.solution.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.dto.base.TimeObject;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinanceTransactionDto extends TimeObject {
    /**
     * Брокерский счёт которому принадлежит транзакция
     */
    private Long brokerAccountId;

    /**
     * Тип транзакции
     */
    private TransactionType transactionType;

    /**
     * Сумма изменения баланса
     */
    private BigDecimal amount;
}
