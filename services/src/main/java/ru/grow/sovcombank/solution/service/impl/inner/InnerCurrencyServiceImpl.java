package ru.grow.sovcombank.solution.service.impl.inner;

import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.entity.CurrencyEntity;
import ru.grow.sovcombank.solution.repository.CurrencyRepository;
import ru.grow.sovcombank.solution.service.inner.InnerCurrencyService;

@Service
public class InnerCurrencyServiceImpl implements InnerCurrencyService {
    private final CurrencyRepository currencyRepository;

    public InnerCurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public CurrencyEntity getCurrencyByCode(String code) {
        return currencyRepository.getCurrencyByCode(code);
    }

    @Override
    public CurrencyEntity save(CurrencyEntity currency) {
        return currencyRepository.save(currency);
    }
}
