package dev.march.financepetproject.service;

import dev.march.financepetproject.dto.PortfolioItemDto;
import dev.march.financepetproject.entity.Asset;
import dev.march.financepetproject.entity.Transaction;
import dev.march.financepetproject.entity.User;
import dev.march.financepetproject.repository.AssetRepository;
import dev.march.financepetproject.repository.TransactionRepository;
import dev.march.financepetproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AssetRepository assetRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, AssetRepository assetRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
    }

    public Transaction buyAsset(String username, String symbol, double amount) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Asset asset = assetRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Asset not found!"));

        double totalCost = amount * asset.getPrice();

        if (user.getBalance() < totalCost) {
            throw new RuntimeException("Not enough balance!");
        }

        user.setBalance(user.getBalance() - totalCost);
        userRepository.save(user);

        Transaction transaction = new Transaction(
                null, user, asset, amount, asset.getPrice(),
                "BUY", LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    public Transaction sellAsset(String username, String symbol, double amount) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Asset asset = assetRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Asset not found!"));

        double totalOwned = transactionRepository.getTotalOwnedAssets(user.getId(), symbol);

        if (totalOwned < amount || amount <= 0) {
            throw new RuntimeException("Not enough assets or amount is invalid!");
        }

        double totalPrice = amount * asset.getPrice();
        user.setBalance(user.getBalance() + totalPrice);
        userRepository.save(user);

        Transaction transaction = new Transaction(
                null, user, asset, -amount, asset.getPrice(),
                "SELL", LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsHistory(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return transactionRepository.findByUserId(user.getId());
    }

    public List<PortfolioItemDto> getUserPortfolio(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        List<Object[]> results = transactionRepository.getUserPortfolio(user.getId());

        return results.stream().map(row -> new PortfolioItemDto(
                (String) row[0], // symbol
                (String) row[1], // name
                ((Number) row[2]).doubleValue(), // total amount
                ((Number) row[3]).doubleValue(), // current price
                ((Number) row[4]).doubleValue()  // avg purchase price
        )).collect(Collectors.toList());
    }
}