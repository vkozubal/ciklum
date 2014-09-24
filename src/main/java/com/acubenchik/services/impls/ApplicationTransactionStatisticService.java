package com.acubenchik.services.impls;

import com.acubenchik.model.ApplicationTransactionStatistic;
import com.acubenchik.repos.ApplicationTransactionStatisticRepository;
import com.acubenchik.services.interfaces.IApplicationTransactionStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class ApplicationTransactionStatisticService implements IApplicationTransactionStatisticService {

    @Autowired
    private ApplicationTransactionStatisticRepository applicationTransactionStatisticRepository;

    @PostConstruct
    @Transactional
    public void init(){
        //TODO: move it to sql script
        ApplicationTransactionStatistic applicationTransactionStatistic = new ApplicationTransactionStatistic();
        applicationTransactionStatistic.setAverageTransactionTime(0l);
        applicationTransactionStatistic.setMaxTransactionTime(0l);
        applicationTransactionStatistic.setMinTransactionTime(0l);
        applicationTransactionStatisticRepository.saveAndFlush(applicationTransactionStatistic);
    }

    @Override
    @Transactional(readOnly = true)
    public ApplicationTransactionStatistic getStatistic() {
        return applicationTransactionStatisticRepository.findOne(1l);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateStatistic(ApplicationTransactionStatistic applicationTransactionStatistic) {
        applicationTransactionStatisticRepository.saveAndFlush(applicationTransactionStatistic);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getAverageTransactionTime(Long userId) {
        return applicationTransactionStatisticRepository.findOne(1l).getAverageTransactionTime();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getMinTransactionTime(Long userId) {
        return applicationTransactionStatisticRepository.findOne(1l).getMinTransactionTime();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getMaxTransactionTime(Long userId) {
        return applicationTransactionStatisticRepository.findOne(1l).getMaxTransactionTime();
    }
}
