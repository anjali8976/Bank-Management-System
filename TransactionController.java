package net.javaguides.banking_app.controller;

import net.javaguides.banking_app.entity.TransactionEntity;
import net.javaguides.banking_app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    // Get full transaction history
    @GetMapping("/{accountId}")
    public List<TransactionEntity> getTransactions(@PathVariable Long accountId) {
        return transactionRepository.findByAccountIdOrderByTimestampDesc(accountId);
    }

    // Get last 5 transactions (Mini Statement)
    @GetMapping("/{accountId}/mini")
    public List<TransactionEntity> getMiniStatement(@PathVariable Long accountId) {
        return transactionRepository.findTop5ByAccountIdOrderByTimestampDesc(accountId);
    }
}
