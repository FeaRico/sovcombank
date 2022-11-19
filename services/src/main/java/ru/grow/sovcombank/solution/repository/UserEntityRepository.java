package ru.grow.sovcombank.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.grow.sovcombank.solution.entity.user.UserEntity;

import java.util.stream.Stream;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    @Query("select ue from UserEntity ue where ue.isValidated = :status")
    Stream<UserEntity> getUsersByValidStatus(@Param("status") Boolean status);

    @Query("select ue from UserEntity ue where ue.isBlocked = :status")
    Stream<UserEntity> getUsersByBlockStatus(@Param("status") Boolean status);
}
