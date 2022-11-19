package ru.grow.sovcombank.solution.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.entity.base.TimeObjectEntity;
import ru.grow.sovcombank.solution.entity.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "broker_account")
@Getter
@Setter
@NoArgsConstructor
public class BrokerAccountEntity extends TimeObjectEntity {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "broker_account_id_seq")
    @SequenceGenerator(name = "broker_account_id_seq", sequenceName = "broker_account_id_seq", initialValue = 10000)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy = "brokerAccount", cascade = CascadeType.ALL)
    private List<FinanceTransactionEntity> transactions;

    public void addTransaction(FinanceTransactionEntity entity) {
        if (transactions == null)
            transactions = new ArrayList<>();
        transactions.add(entity);
        entity.setBrokerAccount(this);
    }

    public void removeTransaction(FinanceTransactionEntity entity) {
        transactions.remove(entity);
        entity.setBrokerAccount(null);
    }
}
