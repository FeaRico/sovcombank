package ru.grow.sovcombank.solution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.grow.sovcombank.solution.dto.FinanceTransactionDto;
import ru.grow.sovcombank.solution.dto.broker.BalanceChangeDto;
import ru.grow.sovcombank.solution.entity.FinanceTransactionEntity;

@Mapper(componentModel = "spring", uses = BrokerAccountMapper.class)
public interface FinanceTransactionMapper {
    FinanceTransactionEntity toServer(BalanceChangeDto balanceChangeDto);

    @Mapping(source = "entity.brokerAccount", target = "brokerAccount", qualifiedByName = "brokerAccountToClient")
    FinanceTransactionDto toClient(FinanceTransactionEntity entity);
}
