package ru.grow.sovcombank.solution.models.rate;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Result implements Serializable {
    private String result;
    private String time_last_update_utc;
    private String base_code;
    private Map<String, Double> conversion_rates;
}
