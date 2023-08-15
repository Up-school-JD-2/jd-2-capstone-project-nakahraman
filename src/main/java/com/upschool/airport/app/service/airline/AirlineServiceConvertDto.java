package com.upschool.airport.app.service.airline;

import com.upschool.airport.app.dto.airline.AirlineDto;
import com.upschool.airport.app.dto.airline.AirlineSaveRequest;
import com.upschool.airport.app.dto.airline.AirlineUpdateRequest;
import com.upschool.airport.app.entity.Airline;
import org.springframework.stereotype.Service;

@Service
public class AirlineServiceConvertDto {

    public AirlineDto entityToAirlineDto(Airline airline){
        return AirlineDto
                .builder()
                .id(airline.getId())
                .airlineCode(airline.getAirlineCode())
                .airlineName(airline.getAirlineName())
                .build();
    }

    public Airline AirlineUpdateRequestToEntity(AirlineUpdateRequest request){
        return Airline.builder()
                .id(request.getId())
                .airlineCode(request.getAirlineCode().toUpperCase())
                .airlineName(request.getAirlineName())
                .build();
    }

    public Airline airlineSaveRequestToEntity(AirlineSaveRequest airlineSaveRequest){

        return Airline
                .builder()
                .airlineCode(airlineSaveRequest.getAirlineCode().toUpperCase())
                .airlineName(airlineSaveRequest.getAirlineName())
                .build();
    }

}
