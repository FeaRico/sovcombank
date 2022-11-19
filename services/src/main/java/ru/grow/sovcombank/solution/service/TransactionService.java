package ru.grow.sovcombank.solution.service;

import ru.grow.sovcombank.solution.dto.FinanceTransactionDto;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.util.List;

public interface TransactionService {
    List<FinanceTransactionDto> getAllByAccountId(Long accountId);

    List<FinanceTransactionDto> getAllByAccountIdAndType(Long accountId, TransactionType type);
}
