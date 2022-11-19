package ru.grow.sovcombank.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.grow.sovcombank.solution.entity.user.SecurityUserEntity;

import java.util.Optional;

public interface SecurityUserRepository extends JpaRepository<SecurityUserEntity, Long> {
    @Query("select se from SecurityUserEntity se where se.username = :username")
    Optional<SecurityUserEntity> findByUsername(@Param("username") String username);
}
