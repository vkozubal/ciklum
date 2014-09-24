package com.acubenchik.repos;

import com.acubenchik.model.TransactionHistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryItemRepository extends JpaRepository<TransactionHistoryItem, Long> {
}
