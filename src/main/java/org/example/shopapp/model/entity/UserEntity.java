package org.example.shopapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Size(min = 2)
    @Column(unique = true, nullable = false)
    private String username;
    @Size(min = 2)
    @NotNull
    private String password;
    @Email
    @Column(unique = true,nullable = false)
    private String email;
    @Positive
    private BigDecimal budget;

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public UserEntity setBudget(BigDecimal budget) {
        this.budget = budget;
        return this;
    }
}
