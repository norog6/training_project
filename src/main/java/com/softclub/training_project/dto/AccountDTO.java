package com.softclub.training_project.dto;

import com.softclub.training_project.entity.enums.Currency;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class AccountDTO {
    private Long id;

    private Currency currency;

    private BigDecimal balance;

    private boolean isBlocked;
}
