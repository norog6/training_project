package com.softclub.training_project.service;

import com.softclub.training_project.dto.CreditDTO;
import com.softclub.training_project.entity.Account;
import com.softclub.training_project.entity.Credit;
import com.softclub.training_project.entity.User;
import com.softclub.training_project.mapper.CreditMapper;
import com.softclub.training_project.repository.AccountRepository;
import com.softclub.training_project.repository.CreditRepository;
import com.softclub.training_project.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    CreditMapper creditMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;


    public void openCredit(Long userId, CreditDTO creditDTO) {
        Credit credit=creditMapper.toEntity(creditDTO);
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));
        credit.setUser(user);
        credit.insert();
        credit.setLoanBalance(credit.getAmount());
        credit.setEndDate(credit.getStartDate().plusMonths(credit.getTimeInMonth()));
        credit.changeSumAndPercent();
        creditRepository.save(credit);
    }
    public String payPerMonth(Long creditId,Long accountId) {
        Credit credit=creditRepository.findById(creditId).orElseThrow(()->new EntityNotFoundException("Credit not found"));
        Account account=accountRepository.findById(accountId).orElseThrow(()->new EntityNotFoundException("Account not found"));
        if (credit.getTimeInMonth()==0){
            throw new RuntimeException("Ваш кредит выплачен");
        }
        if(!credit.getUser().getId().equals(account.getUser().getId())) {
            throw new RuntimeException("Different users");
        }
        if(account.toByn()<credit.getPayMonth()) {
            throw new RuntimeException("Insufficient balance");
        }
        if(account.isBlocked()) {
            throw new RuntimeException("Blocked");
        }
        account.setBalance(account.toByn()-credit.getPayMonth());
        account.toOwnCurrency();
        credit.setLoanBalance(credit.getLoanBalance()-credit.getPayMonth()+credit.getPercent());
        credit.setTimeInMonth(credit.getTimeInMonth()-1);
        creditRepository.save(credit);
        accountRepository.save(account);
        return "было заплачено"+credit.getPayMonth()+" осталось платежей "+credit.getTimeInMonth();
    }

    public String pay(Long creditId,Long accountId,double amount) {
        Credit credit=creditRepository.findById(creditId).orElseThrow(()->new EntityNotFoundException("Credit not found"));
        Account account=accountRepository.findById(accountId).orElseThrow(()->new EntityNotFoundException("Account not found"));
        if(!credit.getUser().getId().equals(account.getUser().getId())) {
            throw new RuntimeException("Different users");
        }
        if(account.toByn()<amount) {
            throw new RuntimeException("Insufficient balance");
        }
        if(amount>credit.getLoanBalance()) {
            throw new RuntimeException("to much");
        }
        if(account.isBlocked()) {
            throw new RuntimeException("Blocked");
        }
        credit.setLoanBalance(credit.getLoanBalance()-amount);
//        Period period = Period.between(credit.getStartDate(), credit.getEndDate());
//        int months = period.getMonths()+period.getYears()*12;
        credit.changeSumAndPercent();
        account.setBalance(account.toByn()-credit.getPayMonth());
        account.toOwnCurrency();
        creditRepository.save(credit);
        accountRepository.save(account);
        return "успешно";
    }
}
