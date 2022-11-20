package ru.grow.sovcombank.solution.service.inner;

import ru.grow.sovcombank.solution.entity.CurrencyEntity;

public interface InnerCurrencyService {
    CurrencyEntity getCurrencyByCode(String code);

    CurrencyEntity save(CurrencyEntity currency);
}
