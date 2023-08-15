package com.upschool.airport.app.service.city;

import com.upschool.airport.app.dto.airport.AirportDto;
import com.upschool.airport.app.dto.city.CityAirportDto;
import com.upschool.airport.app.dto.city.CityDto;
import com.upschool.airport.app.dto.city.CitySaveRequest;
import com.upschool.airport.app.dto.city.CityUpdateRequest;
import com.upschool.airport.app.entity.City;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CityServiceConvertDto {
    public City citySaveRequestToEntity(CitySaveRequest citySaveRequest){

        return City.builder()
                .cityName(citySaveRequest.getCityName())
                .build();

    }

    public CityDto entityToCityDto(City city){

        return CityDto.builder()
                .id(city.getId())
                .cityName(city.getCityName())
                .build();

    }

    public City updateRequestToEntity(CityUpdateRequest request){

        return City.builder()
                .id(request.getId())
                .cityName(request.getCityName())
                .build();
    }

    public CityAirportDto entityToCityAirportDto(City city){
        // convert City to CityAirportDto,
        // when it comes to airports of the city, convert Airport info to AirportDTO
        return CityAirportDto.builder()
                .id(city.getId())
                .cityName(city.getCityName())
                .airports(city.getAirports()
                        .stream()
                        .map(airport -> AirportDto.builder()
                                .id(airport.getId())
                                .airportCode(airport.getAirportCode())
                                .airportName(airport.getAirportName())
                                .build())
                        .collect(Collectors.toList())
                )
                .build();
    }
}
