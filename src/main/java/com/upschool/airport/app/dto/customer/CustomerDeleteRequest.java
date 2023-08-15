package com.upschool.airport.app.dto.customer;

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
public class CustomerDeleteRequest {

    @NotNull(message = "Customer Id cannot be null!")
    private Long id;
}
