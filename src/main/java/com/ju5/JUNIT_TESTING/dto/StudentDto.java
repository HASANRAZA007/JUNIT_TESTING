package com.ju5.JUNIT_TESTING.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class StudentDto {
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;
    @NotBlank(message = "Age cannot be blank")
    @Size(min = 18,message = "Age Must be 18+")
    private int age;
    @NotBlank(message = "Name cannot be blank")
    private String department;
}