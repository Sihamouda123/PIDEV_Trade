package com.PIdevv.PI.Controller;



import com.PIdevv.PI.Entities.Account;
import com.PIdevv.PI.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    private AccountService Accontservice;


    //http://localhost:8080/Account/retrieve-Account
    @GetMapping("/retrieve-Account")
    public List<Account> getAllAccount() {
        return Accontservice.getAllAccount();
    }
    //http://localhost:8080/Account/get-user/{id}
    @GetMapping("/get-Account/{Account-id}")
    public Account getAccountById(@PathVariable("Account-id") Integer IdUser) {
        return Accontservice.getAccountById(IdUser);
    }
    //http://localhost:8080/Account/create-Account
    @PostMapping("/create-Account")
    public Account createAccount(@RequestBody Account account) {
        return Accontservice.createAccount(account);
    }
    //http://localhost:8080/Account/update/{user-id}
  @PutMapping("/update/{account-id}")
    public Account updateAccount(@PathVariable ("account-id") Integer IdAccount, @RequestBody Account account) {
       return Accontservice.updateAccount(IdAccount,account);
  }
    //http://localhost:8080/Account/delete/{UserId}
    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable ("id") Integer IdAccont) {
        Accontservice.deleteAccount(IdAccont);
    }
}
