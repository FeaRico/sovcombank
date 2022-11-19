package ru.grow.sovcombank.solution.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grow.sovcombank.solution.dto.broker.BalanceChangeDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountAddDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountUpdateDto;
import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;
import ru.grow.sovcombank.solution.entity.CurrencyEntity;
import ru.grow.sovcombank.solution.entity.FinanceTransactionEntity;
import ru.grow.sovcombank.solution.entity.user.UserEntity;
import ru.grow.sovcombank.solution.mapper.BrokerAccountMapper;
import ru.grow.sovcombank.solution.mapper.FinanceTransactionMapper;
import ru.grow.sovcombank.solution.service.BrokerAccountService;
import ru.grow.sovcombank.solution.service.inner.InnerBrokerAccountService;
import ru.grow.sovcombank.solution.service.inner.InnerCurrencyService;
import ru.grow.sovcombank.solution.service.inner.InnerUserService;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BrokerAccountServiceImpl implements BrokerAccountService {
    private final InnerBrokerAccountService innerBrokerAccountService;
    private final InnerUserService innerUserService;
    private final InnerCurrencyService innerCurrencyService;
    private final BrokerAccountMapper mapper;
    private final FinanceTransactionMapper financeTransactionMapper;

    public BrokerAccountServiceImpl(InnerBrokerAccountService innerBrokerAccountService, InnerUserService innerUserService, InnerCurrencyService innerCurrencyService, BrokerAccountMapper mapper, FinanceTransactionMapper financeTransactionMapper) {
        this.innerBrokerAccountService = innerBrokerAccountService;
        this.innerUserService = innerUserService;
        this.innerCurrencyService = innerCurrencyService;
        this.mapper = mapper;
        this.financeTransactionMapper = financeTransactionMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrokerAccountDto> getAccountsByUserId(Long userId) {
        return innerBrokerAccountService.getAccountsByUserId(userId)
                .map(mapper::toClient)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BrokerAccountDto getAccountById(Long accountId) {
        return mapper.toClient(innerBrokerAccountService.getById(accountId));
    }

    // TODO: 19.11.2022 Нужна ли проверка по принципалу?
    @Override
    public BrokerAccountDto addAccount(Long userId, BrokerAccountAddDto brokerAccount) {
        UserEntity entity = innerUserService.getById(userId);
        // TODO: 20.11.2022 А если такой валюты нет?
        CurrencyEntity currency = innerCurrencyService.getCurrencyByCode(brokerAccount.getCurrencyCode());

        BrokerAccountEntity accountEntity = mapper.addDtoToServer(brokerAccount);
        accountEntity.setCreatedTime(new Date());
        accountEntity.setUser(entity);
        accountEntity.setBalance(BigDecimal.ZERO);
        accountEntity.setCurrency(currency);

        return mapper.toClient(innerBrokerAccountService.save(accountEntity));
    }

    @Override
    public BrokerAccountDto changeBalance(Long accountId, BalanceChangeDto balanceChangeDto) {
        BrokerAccountEntity accountEntity = innerBrokerAccountService.getById(accountId);
        BigDecimal balance = accountEntity.getBalance();
        BigDecimal newBalance = null;
        if (balanceChangeDto.getTransactionType() == null)
            throw new IllegalStateException();
        BigDecimal amount = balanceChangeDto.getAmount();
        if (balanceChangeDto.getTransactionType().equals(TransactionType.INCOME)) {
            newBalance = balance.add(amount);
        } else if (balanceChangeDto.getTransactionType().equals(TransactionType.EXPANSE)) {
            int i = balance.compareTo(amount);
            if (i < 0) {
                throw new IllegalStateException("Not enough money");
            }
            newBalance = balance.subtract(amount);
        }
        accountEntity.setBalance(newBalance);
        accountEntity.setChangedTime(new Date());

        FinanceTransactionEntity financeTransaction = new FinanceTransactionEntity();
        financeTransaction.setId(null);
        financeTransaction.setCreatedTime(new Date());
        financeTransaction.setType(balanceChangeDto.getTransactionType());
        financeTransaction.setBrokerAccount(accountEntity);
        financeTransaction.setAmount(amount);
        accountEntity.addTransaction(financeTransaction);
        return mapper.toClient(innerBrokerAccountService.update(accountEntity));
    }

    @Override
    public BrokerAccountDto changeBrokerAccount(Long accountId, BrokerAccountUpdateDto brokerAccount) {
        BrokerAccountEntity entity = innerBrokerAccountService.getById(accountId);
        entity.setName(brokerAccount.getAccountName());
        entity.setChangedTime(new Date());
        return mapper.toClient(innerBrokerAccountService.update(entity));
    }
}
