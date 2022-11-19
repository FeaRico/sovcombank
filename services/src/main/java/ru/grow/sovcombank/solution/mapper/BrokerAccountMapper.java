package ru.grow.sovcombank.solution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountAddDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountDto;
import ru.grow.sovcombank.solution.entity.BrokerAccountEntity;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface BrokerAccountMapper {
    @Named("brokerAccountToClient")
    @Mapping(source = "brokerAccount.user", target = "userDto", qualifiedByName = "userToClient")
    @Mapping(source = "brokerAccount.name", target = "accountName")
    BrokerAccountDto toClient(BrokerAccountEntity brokerAccount);

    @Mapping(source = "brokerAccount.accountName", target = "name")
    BrokerAccountEntity addDtoToServer(BrokerAccountAddDto brokerAccount);

    @Named("brokerAccountToServer")
    BrokerAccountEntity toServer(BrokerAccountDto brokerAccount);

}
