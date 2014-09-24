package com.acubenchik.services.interfaces;

import com.acubenchik.model.User;

public interface IUserService {

    public void changeMoneyAmount(Long userId, Double amount);

    public Double getMoneyAmount(Long userId);

    User getUser(Long userId);
}
