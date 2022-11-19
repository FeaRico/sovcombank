package ru.grow.sovcombank.solution.dto.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class IdObject {
    /**
     * Идентификатор
     */
    private Long id;
}
