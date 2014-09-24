package com.acubenchik.repos;

import com.acubenchik.model.ApplicationTransactionStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD operations are transactional by default, no annotation needed
public interface ApplicationTransactionStatisticRepository extends JpaRepository<ApplicationTransactionStatistic, Long> {
}
