package dev.march.financepetproject.dto;

import java.time.LocalDateTime;

public class TransactionDto {
    private String assetSymbol;
    private double amount;
    private double priceAtPurchase;
    private String type; // BUY or SELL
    private LocalDateTime timestamp;

    public String getAssetSymbol() {
        return assetSymbol;
    }

    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionDto() {
    }

    public TransactionDto(String assetSymbol, double amount,
                          double priceAtPurchase, String type,
                          LocalDateTime timestamp) {
        this.assetSymbol = assetSymbol;
        this.amount = amount;
        this.priceAtPurchase = priceAtPurchase;
        this.type = type;
        this.timestamp = timestamp;
    }
}
