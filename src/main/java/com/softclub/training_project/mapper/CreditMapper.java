package com.softclub.training_project.mapper;

import com.softclub.training_project.dto.CreditDTO;
import com.softclub.training_project.entity.Credit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditMapper {
    public Credit toEntity(CreditDTO creditDTO);
    public CreditDTO toDto(Credit credit);
}
