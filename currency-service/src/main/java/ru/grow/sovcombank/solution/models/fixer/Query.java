package ru.grow.sovcombank.solution.models.fixer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Query implements Serializable {
    private String from;
    private String to;
    private String amount;
}
