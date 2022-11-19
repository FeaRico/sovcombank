package ru.grow.sovcombank.solution.service.impl;

import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.dto.broker.BalanceChangeDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountAddDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountUpdateDto;
import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;
import ru.grow.sovcombank.solution.entity.FinanceTransactionEntity;
import ru.grow.sovcombank.solution.entity.user.UserEntity;
import ru.grow.sovcombank.solution.mapper.BrokerAccountMapper;
import ru.grow.sovcombank.solution.mapper.FinanceTransactionMapper;
import ru.grow.sovcombank.solution.service.BrokerAccountService;
import ru.grow.sovcombank.solution.service.inner.InnerBrokerAccountService;
import ru.grow.sovcombank.solution.service.inner.InnerUserService;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrokerAccountServiceImpl implements BrokerAccountService {
    private final InnerBrokerAccountService innerBrokerAccountService;
    private final InnerUserService innerUserService;
    private final BrokerAccountMapper mapper;
    private final FinanceTransactionMapper financeTransactionMapper;

    public BrokerAccountServiceImpl(InnerBrokerAccountService innerBrokerAccountService, InnerUserService innerUserService, BrokerAccountMapper mapper, FinanceTransactionMapper financeTransactionMapper) {
        this.innerBrokerAccountService = innerBrokerAccountService;
        this.innerUserService = innerUserService;
        this.mapper = mapper;
        this.financeTransactionMapper = financeTransactionMapper;
    }

    @Override
    public List<BrokerAccountDto> getAccountsByUserId(Long userId) {
        return innerBrokerAccountService.getAccountsByUserId(userId)
                .map(mapper::toClient)
                .collect(Collectors.toList());
    }

    @Override
    public BrokerAccountDto getAccountById(Long accountId) {
        return mapper.toClient(innerBrokerAccountService.getById(accountId));
    }

    // TODO: 19.11.2022 Нужна ли проверка по принципалу?
    @Override
    public BrokerAccountDto addAccount(Long userId, BrokerAccountAddDto brokerAccount) {
        BrokerAccountEntity accountEntity = mapper.addDtoToServer(brokerAccount);
        UserEntity entity = innerUserService.getById(userId);
        accountEntity.setUser(entity);
        return mapper.toClient(innerBrokerAccountService.save(accountEntity));
    }

    @Override
    public BrokerAccountDto changeBalance(Long accountId, BalanceChangeDto balanceChangeDto) {
        BrokerAccountEntity accountEntity = innerBrokerAccountService.getById(accountId);
        BigDecimal balance = accountEntity.getBalance();
        BigDecimal newBalance = null;
        if (balanceChangeDto.getTransactionType() == null)
            throw new IllegalStateException();
        if (balanceChangeDto.getTransactionType().equals(TransactionType.INCOME)) {
            newBalance = balance.add(balanceChangeDto.getAmount());
        } else if (balanceChangeDto.getTransactionType().equals(TransactionType.EXPANSE)) {
            newBalance = balance.subtract(balanceChangeDto.getAmount());
        }
        accountEntity.setBalance(newBalance);
        FinanceTransactionEntity financeTransaction = financeTransactionMapper.toServer(balanceChangeDto);
        financeTransaction.setBrokerAccount(accountEntity);
        return null;
    }

    @Override
    public BrokerAccountDto changeBrokerAccount(Long accountId, BrokerAccountUpdateDto brokerAccount) {
        BrokerAccountEntity entity = innerBrokerAccountService.getById(accountId);
        entity.setName(brokerAccount.getAccountName());
        return mapper.toClient(innerBrokerAccountService.update(entity));
    }
}
