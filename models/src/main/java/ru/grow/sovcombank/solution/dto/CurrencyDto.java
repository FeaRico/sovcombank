package ru.grow.sovcombank.solution.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.dto.base.TimeObject;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto extends TimeObject {
    /**
     * Код валюты
     */
    private String code;

    /**
     * Значение валюты
     */
    private BigDecimal rate;
}
