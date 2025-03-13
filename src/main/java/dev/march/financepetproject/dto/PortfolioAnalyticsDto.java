package dev.march.financepetproject.dto;

import java.util.Map;

public class PortfolioAnalyticsDto {

    private double totalValue; // current value
    private double totalInvested; // spent money
    private double profitLoss; // profit/loss
    private double profitPercentage; // % of profit/loss
    private Map<String, Double> profitDistribution;

    public PortfolioAnalyticsDto() {
    }

    public PortfolioAnalyticsDto(double totalValue, double totalInvested, double profitLoss,
                                 double profitPercentage, Map<String, Double> profitDistribution) {
        this.totalValue = totalValue;
        this.totalInvested = totalInvested;
        this.profitLoss = profitLoss;
        this.profitPercentage = profitPercentage;
        this.profitDistribution = profitDistribution;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public double getTotalInvested() {
        return totalInvested;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    public double getProfitPercentage() {
        return profitPercentage;
    }

    public Map<String, Double> getProfitDistribution() {
        return profitDistribution;
    }
}
