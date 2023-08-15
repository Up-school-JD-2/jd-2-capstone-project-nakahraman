package com.upschool.airport.app.dto.airline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineDto {

    private Long id;

    private String airlineCode;

    private String airlineName;
}
