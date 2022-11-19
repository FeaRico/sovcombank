package ru.grow.sovcombank.solution.dto.broker;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BrokerAccountAddDto {
    private String accountName;
    private String currencyCode;
}
