package ru.grow.sovcombank.solution.mapper;

import org.mapstruct.Mapper;
import ru.grow.sovcombank.solution.dto.CurrencyDto;
import ru.grow.sovcombank.solution.entity.CurrencyEntity;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    CurrencyDto toClient(CurrencyEntity currency);
}
