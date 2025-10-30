package com.example.proteusspringboot.dto;

import com.example.proteusspringboot.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentRequestDto {

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Email must be a valid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    // Password: At least 8 chars, at least 1 upper, 1 lower, 1 number, 1 special char
//    @Pattern(
//            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
//            message = "Password must be at least 8 characters, include upper, lower, number, and special character"
//    )
    @ValidPassword(message = "Password should contain at lest 1 special character!")
    private String password;

    // Phone number: only digits, 10-15 digits
    @Pattern(
            regexp = "^[0-9]{10,15}$",
            message = "Phone number must contain only digits and be 10 to 15 characters long"
    )
    private String phoneNumber;
}
