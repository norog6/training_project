package com.softclub.training_project.dto;

import com.softclub.training_project.entity.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private long id;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;


    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 60, message = "First name should be between 3 and 60 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 3, max = 60, message = "Last name should be between 3 and 60 characters")
    private String lastName;

    @NotBlank(message = "Phone number is mandatory")
    @Size(min=13,max=13,message = "Phone number should have 13 characters")
    private String phoneNumber;

    private Role roles;

    @NotBlank(message = "Login is mandatory")
    @Size(min = 8, max = 20, message = "Login should be between 8 and 20 characters")
    private String login;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;
}
