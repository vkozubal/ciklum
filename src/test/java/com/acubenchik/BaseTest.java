package com.acubenchik;

import com.acubenchik.model.User;
import com.acubenchik.repos.TransactionHistoryItemRepository;
import com.acubenchik.repos.UserRepository;
import com.acubenchik.services.interfaces.IApplicationTransactionStatisticService;
import com.acubenchik.services.interfaces.IUserService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    private AbstractApplicationContext context;

    @BeforeClass
    public void setUp() {
        context = new ClassPathXmlApplicationContext("context.xml");
        TransactionHistoryItemRepository transactionHistoryItemRepository = (TransactionHistoryItemRepository) getContext().getBean("transactionHistoryItemRepository");
        transactionHistoryItemRepository.deleteAllInBatch();
        IUserService service = getUserService();
        User user1 = service.getUser(1l);
        User user2 = service.getUser(2l);
        user1.setWalletAmount(42d);
        user2.setWalletAmount(42d);
        UserRepository userRepository = (UserRepository) getContext().getBean("userRepository");
        userRepository.saveAndFlush(user1);
        userRepository.saveAndFlush(user2);
    }

    public AbstractApplicationContext getContext() {
        return context;
    }

    public IUserService getUserService() {
        return (IUserService) getContext().getBean("userService");
    }

    public UserRepository getUserRepository() {
        return (UserRepository) getContext().getBean("userRepository");
    }

    public TransactionHistoryItemRepository getTransactionHistoryItemRepository() {
        return (TransactionHistoryItemRepository) getContext().getBean("transactionHistoryItemRepository");
    }
    public IApplicationTransactionStatisticService getApplicationTransactionStatisticService(){
        return (IApplicationTransactionStatisticService) getContext().getBean("applicationTransactionStatisticService");
    }
}
