package ru.grow.sovcombank.solution.service;

import ru.grow.sovcombank.solution.dto.broker.BalanceChangeDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountAddDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountDto;

import java.util.List;

public interface BrokerAccountService {
    List<BrokerAccountDto> getAccountsByUserId(Long userId);

    BrokerAccountDto getAccountById(Long accountId);

    BrokerAccountDto addAccount(Long userId, BrokerAccountAddDto brokerAccount);

    BrokerAccountDto changeBalance(Long accountId, BalanceChangeDto balanceChangeDto);
}
