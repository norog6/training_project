package com.softclub.training_project.dto;

import com.softclub.training_project.entity.enums.DepositType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class DepositDTO {
    private Long id;

    private BigDecimal amount;

    private LocalDate startDate;

    private LocalDate endDate;

    private DepositType depositType;

    private BigDecimal interestRate;
}
