package com.upschool.airport.app.dto.airport;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportSearchNameDto {
    @NotBlank(message = "Airport Name cannot be blank!")
    private String airportName;
}
