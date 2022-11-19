package ru.grow.sovcombank.solution.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grow.sovcombank.solution.dto.broker.BalanceChangeDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountAddDto;
import ru.grow.sovcombank.solution.dto.broker.BrokerAccountDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/broker/account")
public class BrokerAccountController {

    /**
     * Получение брокерских счетов пользователя
     *
     * @param userId идентификатор пользователя
     * @return все брокерские счета по идентификатор пользователя
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BrokerAccountDto>> getAccountsByUserId(@PathVariable Long userId) {
        return null;
    }

    /**
     * Получение брокерского счёта по идентификатору
     *
     * @param accountId идентификатор брокерского счёта
     * @return брокерский счёт
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<BrokerAccountDto> getAccountById(@PathVariable Long accountId) {
        return null;
    }

    /**
     * Добавление брокерского счёта для пользователя
     *
     * @param userId        идентификатор пользователя
     * @param brokerAccount трансферный объект создания брокерского счёта
     * @return сохранённый брокерский счёт
     */
    @PostMapping("/{userId}")
    public ResponseEntity<BrokerAccountDto> addAccount(@PathVariable Long userId, @RequestBody BrokerAccountAddDto brokerAccount) {
        return null;
    }

    /**
     * Изменение баланса на брокерском счету
     *
     * @param accountId        идентификатор брокерского счёта
     * @param balanceChangeDto трансферный объект измененного баланса счёта
     * @return
     */
    @PutMapping("/{accountId}/balance")
    public ResponseEntity<BrokerAccountDto> changeBalance(@PathVariable Long accountId, @RequestBody BalanceChangeDto balanceChangeDto) {
        return null;
    }
}
