package ru.grow.sovcombank.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.grow.sovcombank.solution.entity.CurrencyEntity;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {
    @Query("select ce from CurrencyEntity ce where ce.code = :code")
    CurrencyEntity getCurrencyByCode(@Param("code") String code);
}
