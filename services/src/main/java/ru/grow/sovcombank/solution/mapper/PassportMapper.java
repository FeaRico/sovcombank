package ru.grow.sovcombank.solution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.grow.sovcombank.solution.dto.user.PassportDto;
import ru.grow.sovcombank.solution.entity.PassportEntity;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    @Named("passportToClient")
    PassportDto toClient(PassportEntity passportEntity);

    @Named("passportToServer")
    PassportEntity toServer(PassportDto passportDto);
}
