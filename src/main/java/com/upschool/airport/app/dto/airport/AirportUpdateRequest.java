package com.upschool.airport.app.dto.airport;

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
public class AirportUpdateRequest {

    @NotNull(message = "Airport Id cannot be null!")
    private Long id;

    @NotBlank(message = "Airport Code cannot be blank!")
    @Size(min = 3, max = 3,message = "Please ensure that the Airport Code consists of exactly {min} characters!")
    private String airportCode;

    @NotBlank(message = "Airport Name cannot be blank!")
    @Size(min = 3, max = 50, message = "The Airport Name should have a length ranging from {min} to {max} characters!")
    private String airportName;
}
