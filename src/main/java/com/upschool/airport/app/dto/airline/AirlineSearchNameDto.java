package com.upschool.airport.app.dto.airline;

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
public class AirlineSearchNameDto {

    @NotBlank(message = "Airline Name cannot be blank!")
    private String airlineName;

}
