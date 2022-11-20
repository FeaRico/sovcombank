package ru.grow.sovcombank.solution.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.grow.sovcombank.solution.models.ConvertedCurrencies;

@FeignClient(value = "fixer", url = "https://api.apilayer.com/fixer/")
public interface FixerApi {
    @RequestMapping(method = RequestMethod.GET, value = "convert?to={to}&from={from}&amount={amount}")
    ConvertedCurrencies getConvertedCurrencies(@PathVariable("to") String to,
                                               @PathVariable("from") String from,
                                               @PathVariable("amount") Long amount);
}
