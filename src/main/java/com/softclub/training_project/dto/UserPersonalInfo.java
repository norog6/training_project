package com.softclub.training_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserPersonalInfo {
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

}
