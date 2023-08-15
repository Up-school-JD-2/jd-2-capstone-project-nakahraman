package com.upschool.airport.app.dto.airline;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineSearchCodeDto {

    @NotBlank(message = "Airline Code cannot be blank!")
    private String airlineCode;



}
