package ru.grow.sovcombank.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;

public interface BrokerAccountRepository extends JpaRepository<BrokerAccountEntity, Long> {
}
