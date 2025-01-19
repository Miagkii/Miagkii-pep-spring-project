package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        
        if(accountRepository.findByUsername(account.getUsername()).isPresent()) {    // Check duplicate account
            return null;
        }

        if(!account.getUsername().isBlank() && account.getPassword().length() > 3) {     // Check account creating rules
            Account createdAccount = new Account(account.getUsername(), account.getPassword());
            return accountRepository.save(createdAccount);
        } 
        return null;
    }

    public boolean isDuplicateAccount(Account account) {
        return accountRepository.findByUsername(account.getUsername()).isPresent();
    }

    public Optional<Account> login(Account account) {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

}
