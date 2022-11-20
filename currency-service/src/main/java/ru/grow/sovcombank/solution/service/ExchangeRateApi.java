package ru.grow.sovcombank.solution.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.grow.sovcombank.solution.models.rate.Result;

@FeignClient(value = "exchangeRate", url = "https://v6.exchangerate-api.com/v6/c00db600e6c2df86f7be51f9")
public interface ExchangeRateApi {
    @RequestMapping(method = RequestMethod.GET, value = "latest/{code}")
    Result getResult(@PathVariable("code") String code);
}
