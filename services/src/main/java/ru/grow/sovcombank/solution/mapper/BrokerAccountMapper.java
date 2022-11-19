package ru.grow.sovcombank.solution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountAddDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountDto;
import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;

@Mapper(componentModel = "spring")
public interface BrokerAccountMapper {
    @Named("brokerAccountToClient")
    BrokerAccountDto toClient(BrokerAccountEntity brokerAccount);

    BrokerAccountEntity addDtoToServer(BrokerAccountAddDto brokerAccount);
}
