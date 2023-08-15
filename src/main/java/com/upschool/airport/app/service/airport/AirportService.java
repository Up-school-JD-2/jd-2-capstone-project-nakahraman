package com.upschool.airport.app.service.airport;

import com.upschool.airport.app.entity.Airport;
import com.upschool.airport.app.dto.airport.*;

import java.util.List;

public interface AirportService {
    AirportCityDto saveAirport(AirportSaveRequest airportSaveRequest);
    List<AirportCityDto> getAllAirports();
    AirportCityDto updateAirport(AirportUpdateRequest request);
    void deleteAirport(Long id);
    Airport getAirportById(Long id);
    List<AirportDto> getAirportsByName(AirportSearchNameDto airportSearchDto);
}
