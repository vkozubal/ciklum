package com.acubenchik.aspects;

import com.acubenchik.model.ApplicationTransactionStatistic;
import com.acubenchik.model.TransactionHistoryItem;
import com.acubenchik.model.User;
import com.acubenchik.services.interfaces.IApplicationTransactionStatisticService;
import com.acubenchik.services.interfaces.ITransactionHistoryItemService;
import com.acubenchik.services.interfaces.IUserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Aspect
public class TimeAspect {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITransactionHistoryItemService transactionHistoryItemService;

    @Autowired
    private IApplicationTransactionStatisticService applicationTransactionStatistic;

    @Pointcut(value = "execution(* com.acubenchik.services.interfaces.IUserService+.*cha*(Long, Double,..)) && args(userId, amount,..)", argNames = "userId, amount")
    public void moneyMethod(Long userId, Double amount) {
    }

    //I am not sure is it a good practice to perform DB operations from Advice.
    @Around("moneyMethod(userId, amount)")
    public Object measure(ProceedingJoinPoint pjp, Long userId, Double amount) throws Throwable {
        long start = System.currentTimeMillis();
        Object output = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        User user = userService.getUser(userId);
        TransactionHistoryItem transactionHistoryItem = TransactionHistoryItem.build(user, elapsedTime, amount);
        transactionHistoryItemService.logTransaction(transactionHistoryItem);
        List<TransactionHistoryItem> list = transactionHistoryItemService.getAllTransactions();
        Collections.sort(list);

        //Actually I prefer not to store calculated fields in database, but we should do
        //that according to task
        ApplicationTransactionStatistic applicationTransactionStatisticItem = applicationTransactionStatistic.getStatistic();
        applicationTransactionStatisticItem.setMaxTransactionTime(list.get(list.size() - 1).getDuration());
        applicationTransactionStatisticItem.setMinTransactionTime(list.get(0).getDuration());
        applicationTransactionStatisticItem.setAverageTransactionTime(measureAverageTime(list));
        applicationTransactionStatistic.updateStatistic(applicationTransactionStatisticItem);
        return output;
    }


    private long measureAverageTime(List<TransactionHistoryItem> list) throws Throwable {
        long total = 0l;
        for (TransactionHistoryItem transactionHistoryItem : list) {
            total += transactionHistoryItem.getDuration();
        }
        return total / list.size();
    }

}
