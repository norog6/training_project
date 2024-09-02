package com.softclub.training_project.service;

import com.softclub.training_project.dto.DepositDTO;
import com.softclub.training_project.entity.Account;
import com.softclub.training_project.entity.Deposit;
import com.softclub.training_project.entity.User;
import com.softclub.training_project.mapper.DepositMapper;
import com.softclub.training_project.repository.AccountRepository;
import com.softclub.training_project.repository.DepositRepository;
import com.softclub.training_project.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {
    @Autowired
    DepositRepository depositRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepositMapper depositMapper;
    @Autowired
    AccountRepository accountRepository;

    public void openDeposit(Long userId,Long accountId, DepositDTO depositDTO) {
        Deposit deposit=depositMapper.toEntity(depositDTO);
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));
        Account account=accountRepository.findById(accountId).orElseThrow(()->new EntityNotFoundException("Account not found"));
        if(account.isBlocked()){
            throw new RuntimeException("Account is blocked");
        } else if (account.toByn()<deposit.getAmount()) {
            throw new RuntimeException("Deposit amount exceeded");
        }else if(account.getUser().getId()!=user.getId()){
            throw new RuntimeException("Not 1 user");
        } else {
            deposit.setUser(user);
            deposit.insert();
            deposit.setEndDate(deposit.getStartDate().plusMonths(deposit.getTimeInMonth()));
            deposit.PaymentAmount();
            account.setBalance(account.toByn()-deposit.getAmount());
            account.toOwnCurrency();
            accountRepository.save(account);
            depositRepository.save(deposit);
        }
    }
    public void payToDeposit(Long depositId,Long accountId,double amount) {
        Deposit deposit=depositRepository.findById(depositId).orElseThrow(()->new EntityNotFoundException("Deposit not found"));
        Account account=accountRepository.findById(accountId).orElseThrow(()->new EntityNotFoundException("Account not found"));
        if (account.isBlocked()){
            throw new RuntimeException("Account is blocked");
        } else if (account.toByn()<amount) {
            throw new RuntimeException("Amount not enough");
        }else {
            deposit.setAmount(deposit.getAmount() + amount);
            deposit.PaymentAmount();
            account.setBalance(account.toByn() - amount);
            account.toOwnCurrency();
            depositRepository.save(deposit);
            accountRepository.save(account);
        }
    }
    public void getMoneyFromDeposit(Long depositId,Long accountId) {
        Deposit deposit=depositRepository.findById(depositId).orElseThrow(()->new EntityNotFoundException("Deposit not found"));
        Account account=accountRepository.findById(accountId).orElseThrow(()->new EntityNotFoundException("Account not found"));
        if (account.isBlocked()){
            throw new RuntimeException("Account is blocked");
        }else {
            account.setBalance(account.toByn()+deposit.getPaymentAmount());
            account.toOwnCurrency();
            depositRepository.delete(deposit);
            accountRepository.save(account);
        }
    }
}
