package ru.grow.sovcombank.solution.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.entity.PassportEntity;

import javax.persistence.*;

@Entity
@Table(name = "p_user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends SecurityUserEntity {
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "passport_id")
    private PassportEntity passportEntity;

    @Column(name = "is_block", nullable = false)
    private Boolean isBlocked;

    @Column(name = "is_valid", nullable = false)
    private Boolean isValidated;

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked;
    }
}
