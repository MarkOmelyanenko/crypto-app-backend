package dev.march.financepetproject.service;

import dev.march.financepetproject.dto.PortfolioAnalyticsDto;
import dev.march.financepetproject.entity.Asset;
import dev.march.financepetproject.entity.Transaction;
import dev.march.financepetproject.entity.User;
import dev.march.financepetproject.repository.AssetRepository;
import dev.march.financepetproject.repository.TransactionRepository;
import dev.march.financepetproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioService {

    private final TransactionRepository transactionRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public PortfolioService(TransactionRepository transactionRepository, AssetRepository assetRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    public PortfolioAnalyticsDto getPortfolioAnalytics(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Transaction> transactions = transactionRepository.findByUser(user);
        List<Asset> assets = assetRepository.findAll();

        double totalValue = 0;
        double totalInvested = 0;
        Map<String, Double> assetDistribution = new HashMap<>();

        for (Transaction transaction : transactions) {
            String symbol = transaction.getAsset().getSymbol();
            double priceAtPurchase = transaction.getPriceAtPurchase();
            double amount = transaction.getAmount();

            double currentPrice = assets.stream()
                    .filter(asset -> asset.getSymbol().equals(symbol))
                    .findFirst()
                    .map(Asset::getPrice)
                    .orElse(priceAtPurchase);

            double valueNow = currentPrice * amount;
            double invested = priceAtPurchase * amount;

            totalValue += valueNow;
            totalInvested += invested;

            assetDistribution.put(symbol, assetDistribution.getOrDefault(symbol, 0.0) + valueNow);
        }

        double profitLoss = totalValue - totalInvested;
        double profitPercentage = totalInvested > 0 ? (profitLoss / totalInvested) * 100 : 0;

        return new PortfolioAnalyticsDto(totalValue, totalInvested, profitLoss, profitPercentage, assetDistribution);
    }

}
