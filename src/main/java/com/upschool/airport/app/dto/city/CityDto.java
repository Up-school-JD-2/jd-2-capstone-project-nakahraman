package com.upschool.airport.app.dto.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto {

    private Long id;
    private String cityName;


}
