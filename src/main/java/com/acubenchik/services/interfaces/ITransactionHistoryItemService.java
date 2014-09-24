package com.acubenchik.services.interfaces;

import com.acubenchik.model.TransactionHistoryItem;

import java.util.List;

public interface ITransactionHistoryItemService {

    public List<TransactionHistoryItem> getAllTransactions();

    public void logTransaction(TransactionHistoryItem item);
}
