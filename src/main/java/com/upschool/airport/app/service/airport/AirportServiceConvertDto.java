package com.upschool.airport.app.service.airport;

import com.upschool.airport.app.dto.airport.AirportSaveRequest;
import com.upschool.airport.app.dto.airport.AirportUpdateRequest;
import com.upschool.airport.app.dto.city.CityDto;
import com.upschool.airport.app.entity.Airport;
import com.upschool.airport.app.service.city.CityService;
import com.upschool.airport.app.dto.airport.AirportCityDto;
import com.upschool.airport.app.dto.airport.AirportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AirportServiceConvertDto {

    private final CityService cityService;

    @Transactional(readOnly = true)
    public Airport airportSaveRequestToEntity(AirportSaveRequest airportSaveRequest){

        return Airport.builder()
                .airportCode(airportSaveRequest.getAirportCode().toUpperCase())
                .airportName(airportSaveRequest.getAirportName())
                .city(cityService.getByCityId(airportSaveRequest.getCityId()))
                .build();
    }

    public AirportCityDto entityToAirportCityDto(Airport airport){

        return AirportCityDto .builder()
                .id(airport.getId())
                .airportCode(airport.getAirportCode())
                .airportName(airport.getAirportName())
                .city(CityDto.builder()
                        .id(airport.getCity().getId())
                        .cityName(airport.getCity().getCityName())
                        .build())
                .build();
    }

    public AirportDto entityToAirportDto(Airport airport){

        return AirportDto.builder()
                .id(airport.getId())
                .airportCode(airport.getAirportCode())
                .airportName(airport.getAirportName())
                .build();
    }

    public Airport AirportDtoToEntity(AirportUpdateRequest request, Airport airport){
        return Airport.builder()
                .id(request.getId())
                .airportCode(request.getAirportCode().toUpperCase())
                .airportName(request.getAirportName())
                .city(airport.getCity())//the city already exists in airport, it won't change so, no need to make a call for city
                .build();
    }

}
