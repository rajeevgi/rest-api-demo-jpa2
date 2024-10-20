package com.sprk.rest_api_demo_jpa2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Schema(
        name = "Employee",
        description = "Used to hold Employee Information."
)
public class EmployeeDto {

    @Schema(
        description = "Employee Id",
        example = "Auto Generated"
    )
    private String empId;

    @Schema(
        description = "First Name",
        example = "John"
    )
    @NotBlank(message = "First Name cannot be empty")
    private String firstName;

    @Schema(
        description = "Last Name",
        example = "Doe"
    )
    @NotBlank(message = "Last Name cannot be empty ")
    @Size(min = 5, message = "Last name should contain minimum 5 characters")
    private String lastName;


    @Schema(
        description = "Email - must be unique",
        example = "john@gmail.com"
    )
    @Email(message = "Please provide valid email")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Schema(
        description = "Phone - must be unique",
        example = "+91 1234567890"
    )
    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^[+]{1}(\\d){1,3} (\\d){10,15}$", message = "Please enter valid phone number.")
    private String phone;

    @Schema(
        description = "Department",
        example = "Sales"
    )
    @NotBlank(message = "Department cannot be empty")
    private String department;

    @Schema(
        description = "Designation",
        example = "Manager"
    )
    @NotBlank(message = "Designation cannot be empty")
    private String designation;
}
