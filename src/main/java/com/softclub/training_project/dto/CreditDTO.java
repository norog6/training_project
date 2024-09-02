package com.softclub.training_project.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class CreditDTO {

    private Long id;

    @Min(value = 0)
    private Double amount;

    private LocalDate startDate;

    @Min(value = 3)
    @Max(value = 60)
    private Long timeInMonth;

}
