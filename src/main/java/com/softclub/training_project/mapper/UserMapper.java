package com.softclub.training_project.mapper;

import com.softclub.training_project.dto.UserDTO;
import com.softclub.training_project.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toEntity (UserDTO userDTO);
    public UserDTO toDto(User user);
}
