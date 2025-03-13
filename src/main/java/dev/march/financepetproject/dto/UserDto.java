package dev.march.financepetproject.dto;

public class UserDto {

    private String username;
    private String email;
    private double balance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserDto() {
    }

    public UserDto(String username, String email, double balance) {
        this.username = username;
        this.email = email;
        this.balance = balance;
    }
}