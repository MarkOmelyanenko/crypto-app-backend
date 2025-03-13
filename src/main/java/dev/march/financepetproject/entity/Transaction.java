package dev.march.financepetproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Min(value = -100000, message = "Invalid transaction amount!")
    private double amount;

    @Positive(message = "Price at purchase must be greater than zero!")
    private double priceAtPurchase;

    @NotBlank
    @Pattern(regexp = "BUY|SELL", message = "Type must be buy or sell!")
    private String type; // BUY or SELL

    private LocalDateTime timestamp = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @Min(value = -100000, message = "Invalid transaction amount!")
    public double getAmount() {
        return amount;
    }

    public void setAmount(@Min(value = -100000, message = "Invalid transaction amount!")
                              double amount) {
        this.amount = amount;
    }

    @Positive(message = "Price at purchase must be greater than zero!")
    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(@Positive(message = "Price at purchase must be greater than zero!") double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public @NotBlank @Pattern(regexp = "BUY|SELL", message = "Type must be buy or sell!") String getType() {
        return type;
    }

    public void setType(@NotBlank @Pattern(regexp = "BUY|SELL", message = "Type must be buy or sell!") String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Transaction() {
    }

    public Transaction(Long id, User user, Asset asset, double amount,
                       @Positive(message = "Price must be greater than zero!") double price,
                       String type, LocalDateTime timestamp) {
        this.id = id;
        this.user = user;
        this.asset = asset;
        this.amount = amount;
        this.priceAtPurchase = price;
        this.type = type;
        this.timestamp = timestamp;
    }
}
