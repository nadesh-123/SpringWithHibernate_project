package com.app.model;

import com.app.enums.AccountType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accno;
    @CreationTimestamp
    private Instant createdAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @ManyToOne
    private Customer customer;

    public int getAccno() {
        return accno;
    }

    public void setAccno(int accno) {
        this.accno = accno;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accno=" + accno +
                ", createdAt=" + createdAt +
                ", balance=" + balance +
                ", accountType=" + accountType +
                ", customer=" + customer +
                '}';
    }
}
