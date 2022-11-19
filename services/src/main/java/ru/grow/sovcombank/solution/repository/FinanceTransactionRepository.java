package ru.grow.sovcombank.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.grow.sovcombank.solution.entity.FinanceTransactionEntity;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.util.stream.Stream;

public interface FinanceTransactionRepository extends JpaRepository<FinanceTransactionEntity, Long> {
    @Query("select fte from FinanceTransactionEntity fte where fte.brokerAccount.id = :accountId")
    Stream<FinanceTransactionEntity> getAllByAccountId(@Param("accountId") Long accountId);

    @Query("select fte from FinanceTransactionEntity fte where fte.brokerAccount.id = :accountId and fte.type = :type")
    Stream<FinanceTransactionEntity> getAllByAccountIdAndType(@Param("accountId") Long accountId, @Param("type") TransactionType type);
}
