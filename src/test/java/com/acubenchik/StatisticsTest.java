package com.acubenchik;

import com.acubenchik.model.TransactionHistoryItem;
import com.acubenchik.model.User;
import com.acubenchik.repos.TransactionHistoryItemRepository;
import com.acubenchik.repos.UserRepository;
import com.acubenchik.services.interfaces.IApplicationTransactionStatisticService;
import com.acubenchik.services.interfaces.IUserService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test statistics
 */
public class StatisticsTest extends BaseTest {

    @Test(priority = 1)
    public void testStats() {
        User user = getUserService().getUser(1l);
        List<TransactionHistoryItem> items = new ArrayList<TransactionHistoryItem>();
        items.add(TransactionHistoryItem.build(user, 1000000000000l, 100d));
        items.add(TransactionHistoryItem.build(user, 101l, 101d));
        items.add(TransactionHistoryItem.build(user, 102l, 102d));
        items.add(TransactionHistoryItem.build(user, 1l, 103d));
        getTransactionHistoryItemRepository().save(items);
        IApplicationTransactionStatisticService statService = getApplicationTransactionStatisticService();
        getUserService().changeMoneyAmount(1l, 1d);
        Assert.assertEquals(statService.getMaxTransactionTime(user.getId()), new Long(1000000000000l));
        Assert.assertEquals(statService.getMinTransactionTime(user.getId()), new Long(1l));
    }

    @Test(dependsOnMethods = "testStats")
    public void testStatistics() {
        User user = getUserService().getUser(2l);
        IApplicationTransactionStatisticService statService = getApplicationTransactionStatisticService();
        getTransactionHistoryItemRepository().saveAndFlush(TransactionHistoryItem.build(user, 3000000000000l, 100d));
        getUserService().changeMoneyAmount(2l, 1d);
        Assert.assertEquals(statService.getMaxTransactionTime(user.getId()), new Long(3000000000000l));
        Assert.assertEquals(statService.getMinTransactionTime(user.getId()), new Long(1l));
    }

}
