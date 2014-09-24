package com.acubenchik.model;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import java.util.*;

/**
 * Class to store user records
 */
@Entity(name="UserTable")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    // We should use BigDecimal here, but it didn't work with aspects for me - some exception
    private Double walletAmount;

    @Sort(type = SortType.NATURAL)
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<TransactionHistoryItem> transactions = new ArrayList<TransactionHistoryItem>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(Double walletAmount) {
        this.walletAmount = walletAmount;
    }

    public void setTransactions(List<TransactionHistoryItem> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionHistoryItem> getTransactions() {
        return transactions;
    }

}
