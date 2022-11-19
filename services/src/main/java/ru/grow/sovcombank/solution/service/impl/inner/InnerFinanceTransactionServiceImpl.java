package ru.grow.sovcombank.solution.service.impl.inner;

import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.entity.FinanceTransactionEntity;
import ru.grow.sovcombank.solution.repository.FinanceTransactionRepository;
import ru.grow.sovcombank.solution.service.inner.InnerFinanceTransactionService;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.util.stream.Stream;

@Service
public class InnerFinanceTransactionServiceImpl implements InnerFinanceTransactionService {
    private final FinanceTransactionRepository repository;

    public InnerFinanceTransactionServiceImpl(FinanceTransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Stream<FinanceTransactionEntity> getAllByAccountId(Long accountId) {
        return repository.getAllByAccountId(accountId);
    }

    @Override
    public Stream<FinanceTransactionEntity> getAllByAccountIdAndType(Long accountId, TransactionType type) {
        return repository.getAllByAccountIdAndType(accountId, type);
    }

    @Override
    public FinanceTransactionEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public FinanceTransactionEntity save(FinanceTransactionEntity financeTransaction) {
        if (financeTransaction.getId() != null)
            throw new IllegalStateException();
        return repository.save(financeTransaction);
    }

    @Override
    public FinanceTransactionEntity update(FinanceTransactionEntity financeTransaction) {
        if (financeTransaction.getId() == null)
            throw new IllegalStateException();
        repository.findById(financeTransaction.getId())
                .orElseThrow(IllegalArgumentException::new);
        return repository.save(financeTransaction);
    }

    @Override
    public FinanceTransactionEntity delete(Long id) {
        FinanceTransactionEntity financeTransaction = repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        repository.delete(financeTransaction);
        return financeTransaction;
    }
}
