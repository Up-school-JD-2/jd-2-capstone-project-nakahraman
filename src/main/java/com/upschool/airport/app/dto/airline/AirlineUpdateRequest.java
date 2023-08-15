package com.upschool.airport.app.dto.airline;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineUpdateRequest {

    @NotNull(message = "Airline Id cannot be null!")
    private Long id;
    @NotBlank(message = "Airline Code cannot be blank!")
    @Size(min = 3, max = 3, message = "Please ensure that the Airline Code consists of exactly {min} characters!")
    private String airlineCode;
    @NotBlank(message = "Airline Name cannot be blank!")
    @Size(min = 3, max = 50, message = "The Airline Name should have a length ranging from {min} to {max} characters!")
    private String airlineName;
}
