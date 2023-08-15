package com.upschool.airport.app.dto.payment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDeleteRequest {
    @NotNull(message = "Payment Id cannot be null!")
    private Long id;
}
