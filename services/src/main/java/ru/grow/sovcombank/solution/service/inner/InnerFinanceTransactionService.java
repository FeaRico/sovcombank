package ru.grow.sovcombank.solution.service.inner;

import ru.grow.sovcombank.solution.entity.FinanceTransactionEntity;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.util.stream.Stream;

public interface InnerFinanceTransactionService {
    Stream<FinanceTransactionEntity> getAllByAccountId(Long accountId);

    Stream<FinanceTransactionEntity> getAllByAccountIdAndType(Long accountId, TransactionType type);

    FinanceTransactionEntity getById(Long id);

    FinanceTransactionEntity save(FinanceTransactionEntity financeTransaction);

    FinanceTransactionEntity update(FinanceTransactionEntity financeTransaction);

    FinanceTransactionEntity delete(Long id);
}
