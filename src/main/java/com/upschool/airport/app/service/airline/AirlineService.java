package com.upschool.airport.app.service.airline;

import com.upschool.airport.app.entity.Airline;
import com.upschool.airport.app.dto.airline.*;

import java.util.List;

public interface AirlineService {

    AirlineDto saveAirline(AirlineSaveRequest airlineSaveRequest);
    AirlineDto getAirlineDtoByAirlineCode(AirlineSearchCodeDto airlineCodeDto);
    Airline getByAirlineId(Long id);
    List<AirlineDto> getByAirlineName(AirlineSearchNameDto airlineSearchNameDto);
    List<AirlineDto> getAllAirlines();
    AirlineDto updateAirline(AirlineUpdateRequest request);
    void deleteAirline(Long id);

}

