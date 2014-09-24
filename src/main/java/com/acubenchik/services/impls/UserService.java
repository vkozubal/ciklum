package com.acubenchik.services.impls;

import com.acubenchik.model.User;
import com.acubenchik.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acubenchik.repos.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * We should not annotate interface methods @Transactional
 * because it will not work in class-based proxies
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a test user to work with
     */
    @PostConstruct
    @Transactional
    public void init(){
        //TODO: move it to sql script
        User user = new User();
        user.setWalletAmount(42d);
        userRepository.saveAndFlush(user);
        User user1 = new User();
        user1.setWalletAmount(42d);
        userRepository.saveAndFlush(user1);
    }

    /**
     * Changes the amount of money for current user
     * @param userId The id of user to work with
     * @param amount The amount to change for
     */
    @Override
    @Transactional
    public void changeMoneyAmount(Long userId, Double amount) {
        User user = getUser(userId);
        //Should we use optimistic locking here?
        user.setWalletAmount(user.getWalletAmount() + amount);
        userRepository.saveAndFlush(user);
    }

    /**
     * Get the money amount
     * @param userId The id of user to work with
     * @return the amount of money for user
     */
    @Override
    @Transactional(readOnly = true)
    public Double getMoneyAmount(Long userId) {
        return getUser(userId).getWalletAmount();
    }

    /**
     * Get the user
     * @param userId The id of user to find
     * @return user with the id specified
     */
    @Override
    @Transactional(readOnly = true)
    public User getUser(Long userId) {
        return userRepository.findOne(userId);
    }
}
