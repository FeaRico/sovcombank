package ru.grow.sovcombank.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grow.sovcombank.solution.entity.FinanceTransactionEntity;

public interface FinanceTransactionRepository extends JpaRepository<FinanceTransactionEntity, Long> {
}
