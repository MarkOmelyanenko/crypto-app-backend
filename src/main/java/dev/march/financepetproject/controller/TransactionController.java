package dev.march.financepetproject.controller;

import dev.march.financepetproject.dto.PortfolioItemDto;
import dev.march.financepetproject.entity.Transaction;
import dev.march.financepetproject.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{username}/buy")
    ResponseEntity<Transaction> buyAsset(@PathVariable String username,
                                         @RequestParam String symbol,
                                         @RequestParam double amount) {

        return ResponseEntity.ok(transactionService.buyAsset(username, symbol, amount));
    }

    @PostMapping("/{username}/sell")
    ResponseEntity<Transaction> sellAsset(@PathVariable String username,
                                          @RequestParam String symbol,
                                          @RequestParam double amount) {

        return ResponseEntity.ok(transactionService.sellAsset(username, symbol, amount));
    }

    @GetMapping("/{username}/history")
    ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String username) {
        return ResponseEntity.ok(transactionService.getTransactionsHistory(username));
    }

    @GetMapping("/{username}/portfolio")
    ResponseEntity<List<PortfolioItemDto>> getUserPortfolio(@PathVariable String username) {
        return ResponseEntity.ok(transactionService.getUserPortfolio(username));
    }

}
