package ru.grow.sovcombank.solution.controller.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grow.sovcombank.solution.dto.FinanceTransactionDto;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    /**
     * @param accountId идентификатор брокерского счёта
     * @return все транзакции для брокерского счёта
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<List<FinanceTransactionDto>> getAllByUserAccount(@PathVariable Long accountId) {
        return null;
    }

    /**
     * @param accountId идентификатор брокерского счёта
     * @param type      тип транзакции
     * @return все транзакции по типу операции для брокерского счёта
     */
    @GetMapping("/{accountId}/type")
    public ResponseEntity<List<FinanceTransactionDto>> getAllByAccountIdAndType(@PathVariable Long accountId,
                                                                                @RequestParam TransactionType type) {
        return null;
    }
}
