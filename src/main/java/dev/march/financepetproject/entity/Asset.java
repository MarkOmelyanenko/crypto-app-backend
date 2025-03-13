package dev.march.financepetproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Symbol cannot be empty!")
    @Column(unique = true, nullable = false)
    private String symbol; // BTC, AAPL

    @NotBlank(message = "Name cannot be empty!")
    @Column(unique = true, nullable = false)
    private String name; // Bitcoin, Apple

    @Positive(message = "Price must be greater than zero!")
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Symbol cannot be empty!") String getSymbol() {
        return symbol;
    }

    public void setSymbol(@NotBlank(message = "Symbol cannot be empty!") String symbol) {
        this.symbol = symbol;
    }

    public @NotBlank(message = "Name cannot be empty!") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name cannot be empty!") String name) {
        this.name = name;
    }

    @Positive(message = "Price must be greater than zero!")
    public double getPrice() {
        return price;
    }

    public void setPrice(@Positive(message = "Price must be greater than zero!") double price) {
        this.price = price;
    }

    public Asset() {
    }

    public Asset(Long id, String symbol, String name, double price) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }
}
