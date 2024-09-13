package com.softclub.training_project.entity;

import com.softclub.training_project.entity.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import static com.softclub.training_project.entity.Courses.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private double balance;

    private boolean isBlocked;

    public double toByn() {
        if (this.getCurrency().equals(Currency.BYN)) {
            return this.getBalance();
        } else if (this.getCurrency().equals(Currency.USD)) {
            return this.getBalance()*UsdToByn;
        } else {
            return this.getBalance()*RubToByn;
        }
    }

    public void toOwnCurrency(){
        if (this.getCurrency().equals(Currency.USD)) {
            this.setBalance(this.getBalance()*BynToUsd);
        } else if (this.getCurrency().equals(Currency.RUB)) {
            this.setBalance(this.getBalance()*BynToRub);
        }
    }
    public void swapBetweenAccounts(Account account,double amount,double amountByn) {
        this.setBalance(this.getBalance()-amount);
        account.setBalance(account.toByn()+amountByn);
        account.toOwnCurrency();
    }
}
