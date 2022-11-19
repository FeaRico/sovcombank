package ru.grow.sovcombank.solution.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.entity.base.TimeObjectEntity;
import ru.grow.sovcombank.solution.types.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class FinanceTransactionEntity extends TimeObjectEntity {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "finance_transaction_id_seq")
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "transaction_id_seq", allocationSize = 10000)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "broker_account_id")
    private BrokerAccountEntity brokerAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private BigDecimal amount;
}
