package com.acubenchik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class stores statistics for all the transactions
 */
@Entity
public class ApplicationTransactionStatistic {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long maxTransactionTime;

    private Long minTransactionTime;

    private Long averageTransactionTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaxTransactionTime() {
        return maxTransactionTime;
    }

    public void setMaxTransactionTime(Long maxTransactionTime) {
        this.maxTransactionTime = maxTransactionTime;
    }

    public Long getMinTransactionTime() {
        return minTransactionTime;
    }

    public void setMinTransactionTime(Long minTransactionTime) {
        this.minTransactionTime = minTransactionTime;
    }

    public Long getAverageTransactionTime() {
        return averageTransactionTime;
    }

    public void setAverageTransactionTime(Long averageTransactionTime) {
        this.averageTransactionTime = averageTransactionTime;
    }
}
