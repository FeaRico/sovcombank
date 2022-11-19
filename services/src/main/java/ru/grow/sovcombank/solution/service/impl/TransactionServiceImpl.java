package ru.grow.sovcombank.solution.service.impl;

import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.dto.FinanceTransactionDto;
import ru.grow.sovcombank.solution.mapper.FinanceTransactionMapper;
import ru.grow.sovcombank.solution.service.TransactionService;
import ru.grow.sovcombank.solution.service.inner.InnerFinanceTransactionService;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final InnerFinanceTransactionService innerFinanceTransactionService;
    private final FinanceTransactionMapper mapper;

    public TransactionServiceImpl(InnerFinanceTransactionService innerFinanceTransactionService, FinanceTransactionMapper mapper) {
        this.innerFinanceTransactionService = innerFinanceTransactionService;
        this.mapper = mapper;
    }

    @Override
    public List<FinanceTransactionDto> getAllByAccountId(Long accountId) {
        return innerFinanceTransactionService.getAllByAccountId(accountId)
                .map(mapper::toClient)
                .collect(Collectors.toList());
    }

    @Override
    public List<FinanceTransactionDto> getAllByAccountIdAndType(Long accountId, TransactionType type) {
        return innerFinanceTransactionService.getAllByAccountIdAndType(accountId, type)
                .map(mapper::toClient)
                .collect(Collectors.toList());
    }
}
