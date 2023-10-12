package com.PIdevv.PI.Service;


import com.PIdevv.PI.Entities.Account;
import com.PIdevv.PI.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountrepository;

    @Override
    public List<Account> getAllAccount() {
        return accountrepository.findAll();
    }

    @Override
    public Account getAccountById(Integer IdAccount) {
        return accountrepository.findById(IdAccount).orElse(null);
    }

    @Override
    public Account createAccount(Account account) {
        return accountrepository.save(account);
    }
    @Override
    public Account updateAccount(Integer UserId, Account account) {
        Account existingAccount = accountrepository.findById(UserId).orElse(null);
        if (existingAccount != null) {
            existingAccount.setBalance(account.getBalance());
            existingAccount.setDateOpened(account.getDateOpened());
            return accountrepository.save(existingAccount);
        } else {
            return null;
        }
    }
    @Override
    public void deleteAccount(Integer  IdAccount) {
        accountrepository.deleteById(IdAccount);
    }
}
