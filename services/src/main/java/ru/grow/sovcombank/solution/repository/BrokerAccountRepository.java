package ru.grow.sovcombank.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;

import java.util.stream.Stream;

public interface BrokerAccountRepository extends JpaRepository<BrokerAccountEntity, Long> {
    @Query("select bae from BrokerAccountEntity bae where bae.user.id = :userId")
    Stream<BrokerAccountEntity> getAccountsByUserId(@Param("userId") Long userId);
}
