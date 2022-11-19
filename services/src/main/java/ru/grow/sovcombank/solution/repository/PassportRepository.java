package ru.grow.sovcombank.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grow.sovcombank.solution.entity.PassportEntity;

public interface PassportRepository extends JpaRepository<PassportEntity, Long> {
}
