package com.acubenchik.services.impls;

import com.acubenchik.model.TransactionHistoryItem;
import com.acubenchik.repos.TransactionHistoryItemRepository;
import com.acubenchik.services.interfaces.ITransactionHistoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionHistoryItemService implements ITransactionHistoryItemService {

    @Autowired
    private TransactionHistoryItemRepository transactionHistoryItemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TransactionHistoryItem> getAllTransactions() {
        return transactionHistoryItemRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void logTransaction(TransactionHistoryItem item) {
        transactionHistoryItemRepository.saveAndFlush(item);
    }
}
