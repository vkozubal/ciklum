package com.acubenchik;

import com.acubenchik.model.User;
import com.acubenchik.repos.UserRepository;
import com.acubenchik.services.interfaces.IUserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TwoUsersFlowTest extends BaseTest {

    @Test
    public void testTwoUsers() {
        IUserService userService = getUserService();
        UserRepository userRepository = getUserRepository();
        userService.changeMoneyAmount(1l, 1000d);
        userService.changeMoneyAmount(1l, 1000d);
        userService.changeMoneyAmount(2l, 1200d);
        User user1 = userRepository.findOne(1l);
        User user2 = userRepository.findOne(2l);
        Assert.assertEquals(user1.getTransactions().size(), 2);
        Assert.assertEquals(user2.getTransactions().size(), 1);
        Assert.assertEquals(user1.getTransactions().get(0).getAmount(), 1000d);
        Assert.assertEquals(user2.getTransactions().get(0).getAmount(), 1200d);
        Assert.assertEquals(user1.getWalletAmount(), 2042d);
        Assert.assertEquals(user2.getWalletAmount(), 1242d);
    }


}
