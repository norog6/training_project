package com.softclub.training_project.mapper;

import com.softclub.training_project.dto.DepositDTO;
import com.softclub.training_project.entity.Deposit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepositMapper {
    public Deposit toEntity(DepositDTO depositDTO);
    public DepositDTO toDto(Deposit deposit);
}
