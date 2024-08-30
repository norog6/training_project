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
        credit.setEndDate(credit.getStartDate().plusMonths(credit.getTimeInMonth()));
        creditRepository.save(credit);
    }
    public String payPerMonth(Long creditId,Long accountId) {
        Credit credit=creditRepository.findById(creditId).orElseThrow(()->new EntityNotFoundException("Credit not found"));
        Account account=accountRepository.findById(accountId).orElseThrow(()->new EntityNotFoundException("Account not found"));
        if(!credit.getUser().getId().equals(account.getUser().getId())) {
            throw new RuntimeException("Different users");
        }
        double payment=credit.sumPerMonth();
        if(account.toByn()<payment) {
            throw new RuntimeException("Insufficient balance");
        }
        if(account.isBlocked()){
            throw new RuntimeException("Blocked");
        }
        double procent=(credit.sumPerMonth()*credit.getTimeInMonth()-credit.getAmount())/credit.getTimeInMonth();
        account.setBalance(account.toByn()-payment);
        account.toOwnCurrency();
        credit.setAmount(credit.getAmount()-payment+procent);
        credit.setTimeInMonth(credit.getTimeInMonth()-1);
        creditRepository.save(credit);
        accountRepository.save(account);
        return "было заплачено"+payment+" осталось платежей "+credit.getTimeInMonth();
    }
}
