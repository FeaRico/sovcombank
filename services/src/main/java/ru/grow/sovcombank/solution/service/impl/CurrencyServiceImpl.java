package ru.grow.sovcombank.solution.service.impl;

import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.entity.CurrencyEntity;
import ru.grow.sovcombank.solution.mapper.CurrencyMapper;
import ru.grow.sovcombank.solution.models.rate.Result;
import ru.grow.sovcombank.solution.service.CurrencyService;
import ru.grow.sovcombank.solution.service.ExchangeRateApi;
import ru.grow.sovcombank.solution.service.inner.InnerCurrencyService;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final InnerCurrencyService innerCurrencyService;
    private final CurrencyMapper currencyMapper;
    private final ExchangeRateApi exchangeRateApi;

    public CurrencyServiceImpl(ExchangeRateApi exchangeRateApi, InnerCurrencyService innerCurrencyService, CurrencyMapper currencyMapper) {
        this.exchangeRateApi = exchangeRateApi;
        this.innerCurrencyService = innerCurrencyService;
        this.currencyMapper = currencyMapper;
    }

    @Override
    public CurrencyEntity getCurrencyByCode(String code) {
        CurrencyEntity currency = innerCurrencyService.getCurrencyByCode(code);

        if (currency != null)
            return currency;

        Result result = exchangeRateApi.getResult(code);

        CurrencyEntity currencyFromApi = new CurrencyEntity();
        currencyFromApi.setCreatedTime(new Date());
        currencyFromApi.setCode(result.getBase_code());
        BigDecimal decimal = BigDecimal.valueOf(result.getConversion_rates().get(code));
        currencyFromApi.setRate(decimal);

        return innerCurrencyService.save(currencyFromApi);
    }

    @Override
    public CurrencyMapper getCurrencyMapper() {
        return currencyMapper;
    }
}
