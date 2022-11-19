package ru.grow.sovcombank.solution.service.impl.inner;

import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;
import ru.grow.sovcombank.solution.repository.BrokerAccountRepository;
import ru.grow.sovcombank.solution.service.inner.InnerBrokerAccountService;

import java.util.stream.Stream;

@Service
public class InnerBrokerAccountServiceImpl implements InnerBrokerAccountService {
    private final BrokerAccountRepository repository;

    public InnerBrokerAccountServiceImpl(BrokerAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Stream<BrokerAccountEntity> getAll() {
        return repository.findAll().stream();
    }

    @Override
    public BrokerAccountEntity getById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public BrokerAccountEntity save(BrokerAccountEntity brokerAccount) {
        if (brokerAccount.getId() != null)
            throw new IllegalStateException();
        return repository.save(brokerAccount);
    }

    @Override
    public BrokerAccountEntity update(BrokerAccountEntity brokerAccount) {
        if (brokerAccount.getId() == null)
            throw new IllegalStateException();
        repository.findById(brokerAccount.getId())
                .orElseThrow(IllegalArgumentException::new);
        return repository.save(brokerAccount);
    }

    @Override
    public BrokerAccountEntity delete(Long id) {
        BrokerAccountEntity entity = repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        repository.delete(entity);
        return entity;
    }

    @Override
    public Stream<BrokerAccountEntity> getAccountsByUserId(Long userId) {
        return repository.getAccountsByUserId(userId);
    }
}
