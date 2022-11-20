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
import ru.grow.sovcombank.solution.entity.user.SecurityUserEntity;
import ru.grow.sovcombank.solution.entity.user.UserEntity;
import ru.grow.sovcombank.solution.mapper.BrokerAccountMapper;
import ru.grow.sovcombank.solution.service.BalanceService;
import ru.grow.sovcombank.solution.service.BrokerAccountService;
import ru.grow.sovcombank.solution.service.CurrencyService;
import ru.grow.sovcombank.solution.service.inner.InnerBrokerAccountService;
import ru.grow.sovcombank.solution.service.inner.InnerUserService;
import ru.grow.sovcombank.solution.utils.SecurityUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BrokerAccountServiceImpl implements BrokerAccountService {
    private final InnerBrokerAccountService innerBrokerAccountService;
    private final InnerUserService innerUserService;
    private final CurrencyService currencyService;
    private final BrokerAccountMapper mapper;
    private final BalanceService balanceService;

    public BrokerAccountServiceImpl(InnerBrokerAccountService innerBrokerAccountService, InnerUserService innerUserService, CurrencyService currencyService, BrokerAccountMapper mapper, BalanceService balanceService) {
        this.innerBrokerAccountService = innerBrokerAccountService;
        this.innerUserService = innerUserService;
        this.currencyService = currencyService;
        this.mapper = mapper;
        this.balanceService = balanceService;
    }

    @Transactional(readOnly = true)
    public List<BrokerAccountDto> getAccountsByUser() {
        SecurityUserEntity securityUser = SecurityUtils.getUserFromContext();
        return innerBrokerAccountService.getAccountsByUserId(securityUser.getId())
                .map(mapper::toClient)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BrokerAccountDto getAccountById(Long accountId) {
        return mapper.toClient(innerBrokerAccountService.getById(accountId));
    }

    @Override
    public BrokerAccountDto addAccount(BrokerAccountAddDto brokerAccount) {
        SecurityUserEntity securityUser = SecurityUtils.getUserFromContext();

        UserEntity entity = innerUserService.getById(securityUser.getId());

        CurrencyEntity currency = currencyService.getCurrencyByCode(brokerAccount.getCurrencyCode());

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

        SecurityUserEntity securityUser = SecurityUtils.getUserFromContext();

        if (!securityUser.getId().equals(accountEntity.getUser().getId()))
            throw new IllegalStateException();

        BrokerAccountDto accountDto = mapper.toClient(accountEntity);
        BrokerAccountDto updatedAccount = balanceService.calculate(accountDto, balanceChangeDto);

        FinanceTransactionEntity financeTransaction = new FinanceTransactionEntity();
        financeTransaction.setId(null);
        financeTransaction.setCreatedTime(new Date());
        financeTransaction.setType(balanceChangeDto.getTransactionType());
        financeTransaction.setBrokerAccount(accountEntity);
        financeTransaction.setAmount(balanceChangeDto.getAmount());

        accountEntity.setBalance(updatedAccount.getBalance());
        accountEntity.setChangedTime(new Date());
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
