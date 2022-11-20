package ru.grow.sovcombank.solution.service;

import ru.grow.sovcombank.solution.dto.broker.BalanceChangeDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountAddDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountUpdateDto;

import java.util.List;

public interface BrokerAccountService {
    List<BrokerAccountDto> getAccountsByUser();

    BrokerAccountDto getAccountById(Long accountId);

    BrokerAccountDto addAccount(BrokerAccountAddDto brokerAccount);

    BrokerAccountDto changeBalance(Long accountId, BalanceChangeDto balanceChangeDto);

    BrokerAccountDto changeBrokerAccount(Long accountId, BrokerAccountUpdateDto brokerAccount);
}
