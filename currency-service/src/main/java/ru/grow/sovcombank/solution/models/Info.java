package ru.grow.sovcombank.solution.models;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Info implements Serializable {
    private Timestamp timestamp;
    private Double rate;
}
