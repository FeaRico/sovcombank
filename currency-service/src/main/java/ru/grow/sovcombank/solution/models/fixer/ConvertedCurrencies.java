package ru.grow.sovcombank.solution.models.fixer;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class ConvertedCurrencies implements Serializable {
    private Boolean success;
    private Query query;
    private Info info;
    private String historical;
    private Date date;
    private Double result;
}
