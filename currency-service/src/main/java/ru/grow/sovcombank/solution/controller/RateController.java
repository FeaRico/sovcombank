package ru.grow.sovcombank.solution.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grow.sovcombank.solution.models.rate.Result;
import ru.grow.sovcombank.solution.service.ExchangeRateApi;

@RestController
@RequestMapping("/api/v1/rate")
public class RateController {
    private final ExchangeRateApi exchangeRateApi;

    public RateController(ExchangeRateApi exchangeRateApi) {
        this.exchangeRateApi = exchangeRateApi;
    }

    @GetMapping("/latest/{code}")
    public ResponseEntity<Result> getResult(@PathVariable("code") String code) {
        return ResponseEntity.ok(exchangeRateApi.getResult(code));
    }
}
