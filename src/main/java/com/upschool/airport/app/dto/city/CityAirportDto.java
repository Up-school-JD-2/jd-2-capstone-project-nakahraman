package com.upschool.airport.app.dto.city;

import com.upschool.airport.app.dto.airport.AirportDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityAirportDto {

    private Long id;
    private String cityName;
    private List<AirportDto> airports;
}
