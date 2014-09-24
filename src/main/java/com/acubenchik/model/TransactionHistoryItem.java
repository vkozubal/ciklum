package com.acubenchik.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class to store transaction records
 */
@Entity
public class TransactionHistoryItem implements Comparable<TransactionHistoryItem> {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Long duration;

    private Double amount;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getDuration() {

        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public static TransactionHistoryItem build(User user, Long duration, Double amount) {
        TransactionHistoryItem item = new TransactionHistoryItem();
        item.setDuration(duration);
        item.setAmount(amount);
        item.setUser(user);
        item.setDate(new Date(System.currentTimeMillis()));
        return item;
    }

    @Override
    public int compareTo(TransactionHistoryItem o) {
        return this.getDuration().compareTo(o.getDuration());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionHistoryItem that = (TransactionHistoryItem) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
