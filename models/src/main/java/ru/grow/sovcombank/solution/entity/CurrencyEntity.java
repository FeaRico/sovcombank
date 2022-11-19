package ru.grow.sovcombank.solution.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.entity.base.TimeObjectEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "currency")
@Getter
@Setter
@NoArgsConstructor
public class CurrencyEntity extends TimeObjectEntity {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_id_seq")
    @SequenceGenerator(name = "currency_id_seq", sequenceName = "currency_id_seq", allocationSize = 500)
    private Long id;

    // TODO: 19.11.2022 Есть ли курсы с длиной строки > 3?
    @Column(unique = true, nullable = false, length = 3)
    private String code;

    @Column(nullable = false)
    private BigDecimal rate;
}
