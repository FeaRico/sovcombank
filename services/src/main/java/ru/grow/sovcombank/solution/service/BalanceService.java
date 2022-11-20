package ru.grow.sovcombank.solution.service;

import ru.grow.sovcombank.solution.dto.broker.BalanceChangeDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountDto;

/**
 * Сервис управления балансом брокерских счетов
 */
public interface BalanceService {
    /**
     * Вычислить новый баланс счёта
     *
     * @param account       брокерский счёт
     * @param balanceChange изменение счёта
     * @return изменённый счёт
     */
    BrokerAccountDto calculate(BrokerAccountDto account, BalanceChangeDto balanceChange);
}
