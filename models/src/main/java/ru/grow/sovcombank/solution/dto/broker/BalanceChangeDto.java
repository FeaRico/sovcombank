package ru.grow.sovcombank.solution.dto.broker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BalanceChangeDto {
    private TransactionType transactionType;
    private BigDecimal amount;
}
