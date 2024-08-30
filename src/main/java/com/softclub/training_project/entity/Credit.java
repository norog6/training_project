package com.softclub.training_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Double amount;

    private Long timeInMonth;

    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

//    @Enumerated(EnumType.STRING)
//    private CreditType creditType; // Единичные или ежемесячные выплаты

    private Double interestRate;

    public void insert(){
        if(this.getTimeInMonth() <12){
            this.setInterestRate(0.50);
        } else if (this.timeInMonth <30) {
            this.setInterestRate(0.35);
        }else this.setInterestRate(0.25);
    }
    public double sumPerMonth(){
        return (this.getAmount() * this.getInterestRate()/12 * Math.pow(1 + this.getInterestRate()/12, this.getTimeInMonth())) /
        (Math.pow(1 + this.getInterestRate()/12, this.getTimeInMonth()) - 1);
    }
}
