package com.softclub.training_project.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class DepositDTO {
    private Long id;

    private double amount;

    private LocalDate startDate;

    @Min(value = 3)
    @Max(value = 36)
    private Long timeInMonth;

}
