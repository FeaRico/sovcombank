package ru.grow.sovcombank.solution.service.impl;

import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.dto.broker.BalanceChangeDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountDto;
import ru.grow.sovcombank.solution.service.BalanceService;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.math.BigDecimal;

@Service
public class BalanceServiceImpl implements BalanceService {
    @Override
    public BrokerAccountDto calculate(BrokerAccountDto account, BalanceChangeDto balanceChange) {
        BigDecimal balance = account.getBalance();
        BigDecimal newBalance = null;

        if (balanceChange.getTransactionType() == null)
            throw new IllegalStateException();

        BigDecimal amount = balanceChange.getAmount();
        if (balanceChange.getTransactionType().equals(TransactionType.INCOME)) {
            newBalance = balance.add(amount);
        } else if (balanceChange.getTransactionType().equals(TransactionType.EXPANSE)) {
            int i = balance.compareTo(amount);
            if (i < 0) {
                throw new IllegalStateException("Not enough money");
            }
            newBalance = balance.subtract(amount);
        }
        account.setBalance(newBalance);
        return account;
    }
}
