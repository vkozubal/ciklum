package com.acubenchik.services.interfaces;

import com.acubenchik.model.ApplicationTransactionStatistic;

public interface IApplicationTransactionStatisticService {

    public ApplicationTransactionStatistic getStatistic();

    public void updateStatistic(ApplicationTransactionStatistic applicationTransactionStatistic);

    Long getAverageTransactionTime(Long userId);

    Long getMinTransactionTime(Long userId);

    Long getMaxTransactionTime(Long userId);
}
