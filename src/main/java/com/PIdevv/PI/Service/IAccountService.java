package com.PIdevv.PI.Service;



import com.PIdevv.PI.Entities.Account;

import java.util.List;

public interface IAccountService {
    public List<Account> getAllAccount();

    Account getAccountById(Integer IdAccount);

    Account createAccount(Account account);
    Account updateAccount(Integer IdAccount, Account account);
    void deleteAccount(Integer  Account);
}
