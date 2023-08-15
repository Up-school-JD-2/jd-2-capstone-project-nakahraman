package com.upschool.airport.app.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSaveRequest {

    @NotNull(message = "Identity Number cannot be null!")
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$",
            message = "Invalid T.C. Identity Number format!")
    private String identityNumber;

    @NotBlank(message = "Customer Name cannot be blank!")
    @Size(min = 2, max = 50, message = "The Customer Name should have a length ranging from {min} to {max} characters!")
    private String name;

    @NotBlank(message = "Customer Surname cannot be blank!")
    @Size(min = 2, max = 50, message = "The Customer Surname should have a length ranging from {min} to {max} characters!")
    private String surname;

    @NotBlank(message = "Phone cannot be blank!")
    @Pattern(regexp = "\\+?[1-9][0-9]{7,14}",
            message = "Invalid Phone Number format!")
    private String phone;

    @NotBlank(message = "Email cannot be blank!")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Invalid Email format!")
    private String email;
}
