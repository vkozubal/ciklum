package com.acubenchik;

import com.acubenchik.model.User;
import com.acubenchik.services.interfaces.IUserService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class OneUserFlowTest extends BaseTest {

    @Test(priority = 1)
    public void testInitialState() {

        List<User> users = getUserRepository().findAll();
        Assert.assertEquals(users.size(), 2);
        Assert.assertEquals(users.get(0).getTransactions().size(), 0);
        Assert.assertEquals(users.get(1).getTransactions().size(), 0);
    }

    @Test(dependsOnMethods = "testInitialState")
    public void testChangeAmount() {
        getUserService().changeMoneyAmount(1l, 444d);
        User user =  getUserService().getUser(1l);
        Assert.assertEquals(user.getTransactions().size(), 1);
        Assert.assertEquals(user.getTransactions().get(0).getAmount(), 444d);
        Assert.assertEquals(user.getWalletAmount(), 486d);
    }

    @Test(dependsOnMethods = "testChangeAmount")
    public void testChangeTransactions() {
        IUserService service = getUserService();
        service.changeMoneyAmount(1l, 1d);
        Double amount = service.getMoneyAmount(1l);
        Assert.assertEquals(amount, 487d);
        Assert.assertEquals(service.getUser(1l).getTransactions().size(), 2);
    }

    @Test(dependsOnMethods = "testChangeTransactions")
    public void testDecreaseAmount() {
        IUserService service = getUserService();
        service.changeMoneyAmount(1l, -1000d);
        Double amount = service.getMoneyAmount(1l);
        Assert.assertEquals(amount, -513d);
        Assert.assertEquals(service.getUser(1l).getTransactions().size(), 3);
    }

    @Test(dependsOnMethods = "testDecreaseAmount")
    public void testSimultaneouslyChange() {
        IUserService service = getUserService();
        service.changeMoneyAmount(1l, 1000d);
        service.changeMoneyAmount(1l, 1000d);
        service.changeMoneyAmount(1l, 1000d);
        service.changeMoneyAmount(1l, 1000d);
        Double amount = service.getMoneyAmount(1l);
        Assert.assertEquals(amount, 3487d);
        Assert.assertEquals(service.getUser(1l).getTransactions().size(), 7);
    }
}
