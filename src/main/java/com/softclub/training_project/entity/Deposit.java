package com.softclub.training_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;


    private double amount;

    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    private long timeInMonth;

    private double interestRate;
    @Column(name="paementAmount")
    private double paymentAmount;

    public void insert() {
        if (this.getTimeInMonth() < 9) {
            this.setInterestRate(0.1);
        } else if (this.getTimeInMonth() < 18) {
            this.setInterestRate(0.15);
        } else this.setInterestRate(0.2);
    }
    public void PaymentAmount() {
        this.setPaymentAmount(this.getAmount() * Math.pow(1+this.getInterestRate()/12,this.getTimeInMonth()));
    }

}
