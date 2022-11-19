package ru.grow.sovcombank.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grow.sovcombank.solution.entity.user.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}
