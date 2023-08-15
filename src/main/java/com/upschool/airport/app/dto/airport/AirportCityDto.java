package com.upschool.airport.app.dto.airport;

import com.upschool.airport.app.dto.city.CityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportCityDto {

    private Long id;
    private String airportCode;
    private String airportName;
    private CityDto city;
}
