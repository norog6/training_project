package com.softclub.training_project.service;

import com.softclub.training_project.dto.AccountDTO;
import com.softclub.training_project.entity.Account;
import com.softclub.training_project.entity.User;
import com.softclub.training_project.entity.enums.Currency;
import com.softclub.training_project.mapper.AccountMapper;
import com.softclub.training_project.repository.AccountRepository;
import com.softclub.training_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.softclub.training_project.entity.Courses.*;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    UserRepository userRepository;

    public void Transfer(Long fromAccountID, Long toAccountID, double amount) {
        Account fromAccount = accountRepository.findById(fromAccountID).orElseThrow(() -> new RuntimeException("Account not found"));
        Account toAccount = accountRepository.findById(toAccountID).orElseThrow(() -> new RuntimeException("Account not found"));
        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        if (fromAccount.isBlocked() || toAccount.isBlocked()) {
            throw new RuntimeException("Account blocked");
        }
        if(fromAccount.getCurrency().equals(fromAccount.getCurrency()) ) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
        }
        else {
            double amountByn = amount;
            if (fromAccount.getCurrency().equals(Currency.USD)) {
                amountByn = amount * UsdToByn;
            } else if (fromAccount.getCurrency().equals(Currency.RUB)) {
                amountByn = amount * RubToByn;
            }
            fromAccount.swapBetweenAccounts(toAccount, amount, amountByn);
        }
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
    public Account openAccount(Long userId, AccountDTO accountDTO) {
        Account account = accountMapper.toEntity(accountDTO);
        User user =userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        account.setUser(user);
        account.setBlocked(false);
        return accountRepository.save(account);
    }
    public void blockAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("account not found"));
        account.setBlocked(true);
        accountRepository.save(account);
    }

    public void unblockAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("account not found"));
        account.setBlocked(false);
        accountRepository.save(account);
    }
    public void addMoneyToAccount(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }
}
