package ru.grow.sovcombank.solution.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.entity.base.TimeObjectEntity;
import ru.grow.sovcombank.solution.entity.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "broker_account")
@Getter
@Setter
@NoArgsConstructor
public class BrokerAccountEntity extends TimeObjectEntity {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "broker_account_id_seq")
    @SequenceGenerator(name = "broker_account_id_seq", sequenceName = "broker_account_id_seq", allocationSize = 10000)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;

    @Column(nullable = false)
    private BigDecimal balance;
}
