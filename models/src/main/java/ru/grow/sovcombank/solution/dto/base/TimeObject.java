package ru.grow.sovcombank.solution.dto.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class TimeObject extends IdObject {
    /**
     * Время создания объекта
     */
    private Date createdTime;

    /**
     * Время изменения объекта
     */
    private Date changedTime;
}
