package ru.grow.sovcombank.solution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.grow.sovcombank.solution.dto.FinanceTransactionDto;
import ru.grow.sovcombank.solution.dto.broker.BalanceChangeDto;
import ru.grow.sovcombank.solution.entity.FinanceTransactionEntity;

@Mapper(componentModel = "spring", uses = BrokerAccountMapper.class)
public interface FinanceTransactionMapper {
    @Mapping(source = "balanceChangeDto.transactionType", target = "type")
    FinanceTransactionEntity toServer(BalanceChangeDto balanceChangeDto);

    @Mapping(source = "entity.brokerAccount.id", target = "brokerAccountId")
    @Mapping(source = "entity.type", target = "transactionType")
    FinanceTransactionDto toClient(FinanceTransactionEntity entity);
}
