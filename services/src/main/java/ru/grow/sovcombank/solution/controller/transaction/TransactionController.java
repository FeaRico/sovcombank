package ru.grow.sovcombank.solution.controller.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grow.sovcombank.solution.dto.FinanceTransactionDto;
import ru.grow.sovcombank.solution.service.TransactionService;
import ru.grow.sovcombank.solution.types.TransactionType;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * @param accountId идентификатор брокерского счёта
     * @return все транзакции для брокерского счёта
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<List<FinanceTransactionDto>> getAllByAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getAllByAccountId(accountId));
    }

    /**
     * @param accountId идентификатор брокерского счёта
     * @param type      тип транзакции
     * @return все транзакции по типу операции для брокерского счёта
     */
    @GetMapping("/{accountId}/type")
    public ResponseEntity<List<FinanceTransactionDto>> getAllByAccountIdAndType(@PathVariable Long accountId,
                                                                                @RequestParam TransactionType type) {
        return ResponseEntity.ok(transactionService.getAllByAccountIdAndType(accountId, type));
    }
}
