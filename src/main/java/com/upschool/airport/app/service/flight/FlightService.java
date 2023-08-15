package com.upschool.airport.app.service.flight;


import com.upschool.airport.app.dto.flight.FlightDto;
import com.upschool.airport.app.dto.flight.FlightSaveRequest;
import com.upschool.airport.app.dto.flight.FlightUpdateRequest;
import com.upschool.airport.app.entity.Flight;

import java.util.List;

public interface FlightService {

    FlightDto saveFlight(FlightSaveRequest airlineSaveRequest);
    FlightDto getFlightDtoById(Long id);
    Flight getFlightById(Long id);
    List<FlightDto> getAllFlights();
    FlightDto updateFlight(FlightUpdateRequest request);
    void deleteFlight(Long id);
    void bookSeat(Long id);
    void cancelBooking(Long id);

}
