package com.softclub.training_project.mapper;

import com.softclub.training_project.dto.AccountDTO;
import com.softclub.training_project.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    public Account toEntity(AccountDTO accountDTO);
    public AccountDTO toDto(Account account);
}
