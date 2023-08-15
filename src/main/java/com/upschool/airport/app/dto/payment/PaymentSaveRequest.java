package com.upschool.airport.app.dto.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSaveRequest {

    @NotNull(message = "Ticket Id cannot be null!")
    private Long ticketId;

    @NotNull(message = "Price cannot be null!")
    private Double price;

    @NotBlank(message = "Credit Card Number cannot be blank!")
    @Pattern(regexp = "(\\d{4}[- ,]?\\d{4}[- ,]?\\d{4}[- ,]?\\d{4})",
            message = "Invalid credit card number format!")
    private String creditCardNumber;

}
