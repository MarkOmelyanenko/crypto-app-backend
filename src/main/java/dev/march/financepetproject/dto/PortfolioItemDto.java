package dev.march.financepetproject.dto;

public class PortfolioItemDto {
    private String symbol;
    private String name;
    private double totalAmount;
    private double currentPrice;
    private double avgPurchasePrice;

    public PortfolioItemDto() {
    }

    public PortfolioItemDto(String symbol, String name, double totalAmount,
                            double currentPrice, double avgPurchasePrice) {
        this.symbol = symbol;
        this.name = name;
        this.totalAmount = totalAmount;
        this.currentPrice = currentPrice;
        this.avgPurchasePrice = avgPurchasePrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getAvgPurchasePrice() {
        return avgPurchasePrice;
    }

    public void setAvgPurchasePrice(double avgPurchasePrice) {
        this.avgPurchasePrice = avgPurchasePrice;
    }
}
