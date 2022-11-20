package ru.grow.sovcombank.solution.service;

import ru.grow.sovcombank.solution.entity.CurrencyEntity;
import ru.grow.sovcombank.solution.mapper.CurrencyMapper;

public interface CurrencyService {

    CurrencyEntity getCurrencyByCode(String code);

    CurrencyMapper getCurrencyMapper();
}
