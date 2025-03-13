package dev.march.financepetproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be empty!")
    @Column(unique = true, nullable = false)
    private String username;

    @Email(message = "Wrong email!")
    @NotBlank(message = "Email cannot be empty!")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    @Column(nullable = false)
    private String password;

    private double balance = 1000.0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Username cannot be empty!") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username cannot be empty!") String username) {
        this.username = username;
    }

    public @Email(message = "Wrong email!") @NotBlank(message = "Email cannot be empty!") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Wrong email!") @NotBlank(message = "Email cannot be empty!") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password cannot be empty!") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password cannot be empty!") String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User() {
    }

    public User(double balance, String password, String email, String username, Long id) {
        this.balance = balance;
        this.password = password;
        this.email = email;
        this.username = username;
        this.id = id;
    }
}
