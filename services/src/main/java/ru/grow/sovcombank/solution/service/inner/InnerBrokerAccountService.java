package ru.grow.sovcombank.solution.service.inner;

import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;

import java.util.stream.Stream;

public interface InnerBrokerAccountService {
    Stream<BrokerAccountEntity> getAll();

    BrokerAccountEntity getById(Long id);

    BrokerAccountEntity save(BrokerAccountEntity brokerAccount);

    BrokerAccountEntity update(BrokerAccountEntity brokerAccount);

    BrokerAccountEntity delete(Long id);

    Stream<BrokerAccountEntity> getAccountsByUserId(Long userId);
}
