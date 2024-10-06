package com.scm.scm.form;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {

    @NotBlank(message = "Name is required")
    @Size(min=3, message = "Minimum 3 character is required")
    private String name;
    @Email(message = "Invalid Email ID")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min=4 , message = "Minimum 4 character required")
    private String password;
    @NotBlank(message = "About is required")
    private String about;
    @NotBlank(message = "Phone Number is required")
    @Size(min = 10 ,max = 13)
    @Digits(integer = 10, message = "Only number required", fraction = 0) 
    private String phoneNumber;
}
