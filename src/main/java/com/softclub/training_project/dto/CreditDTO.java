package com.softclub.training_project.dto;

import com.softclub.training_project.entity.enums.CreditType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class CreditDTO {

    private Long id;

    @NotBlank(message = "amount is mandatory")
    private BigDecimal amount;

    private LocalDate startDate;

    private LocalDate endDate;

    private CreditType creditType;

    private BigDecimal interestRate;

}
