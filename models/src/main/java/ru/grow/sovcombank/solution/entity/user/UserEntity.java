package ru.grow.sovcombank.solution.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;
import ru.grow.sovcombank.solution.entity.PassportEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<BrokerAccountEntity> brokerAccounts;

    public void addBrokerAccount(BrokerAccountEntity brokerAccount) {
        if (brokerAccounts == null)
            brokerAccounts = new ArrayList<>();
        brokerAccounts.add(brokerAccount);
        brokerAccount.setUser(this);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(passportEntity, that.passportEntity) && Objects.equals(isBlocked, that.isBlocked) && Objects.equals(isValidated, that.isValidated) && Objects.equals(brokerAccounts, that.brokerAccounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportEntity, isBlocked, isValidated, brokerAccounts);
    }
}
