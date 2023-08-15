package com.upschool.airport.app.dto.airport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportDto {

    private Long id;
    private String airportCode;
    private String airportName;

}
