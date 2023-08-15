package com.upschool.airport.app.service.flight;

import com.upschool.airport.app.dto.flight.FlightDto;
import com.upschool.airport.app.dto.flight.FlightSaveRequest;
import com.upschool.airport.app.dto.flight.FlightUpdateRequest;
import com.upschool.airport.app.entity.Flight;
import com.upschool.airport.app.exception.DuplicateEntityException;
import com.upschool.airport.app.exception.NoSuchEntityFoundException;
import com.upschool.airport.app.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightServiceConvertDto convertDto;
    private final FlightRepository flightRepository;
    @Override
    @Transactional
    public FlightDto saveFlight(FlightSaveRequest request) {

        checkDuplicateAirlineAndRoute(request.getAirlineId(), request.getRouteId());
        Flight flight = convertDto.flightSaveRequestToEntity(request);
        return convertDto.entityToFlightDto(flightRepository.save(flight));
    }

    private void checkDuplicateAirlineAndRoute(Long airlineId, Long routeId){

        if(flightRepository.existsByAirlineIdAndRouteId(airlineId, routeId)) {
            throw new DuplicateEntityException("Flight with AIRLINE ID " +
                                               airlineId + " and ROUTE ID " + routeId +
                                               " already exists!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public FlightDto getFlightDtoById(Long id) {

        Flight flight = getFlightById(id);
        return  convertDto.entityToFlightDto(flight);
    }

    @Override
    @Transactional(readOnly = true)
    public Flight getFlightById(Long id){

        return flightRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityFoundException("Flight with ID " + id + " not found!"));
    }


    @Override
    @Transactional(readOnly = true)
    public List<FlightDto> getAllFlights() {

       List<Flight> flights = flightRepository.findAll();
       return flights.stream()
               .map(flight -> convertDto.entityToFlightDto(flight))
               .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FlightDto updateFlight(FlightUpdateRequest request) {

        getFlightById(request.getId());
        checkDuplicateAirlineAndRoute(request.getAirlineId(), request.getRouteId());
        Flight flight = convertDto.updateRequestToEntity(request);
        return convertDto.entityToFlightDto(flightRepository.save(flight));
    }

    @Override
    @Transactional
    public void deleteFlight(Long id) {

        getFlightById(id);
        flightRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void bookSeat(Long id){

        Flight flight = getFlightById(id);
        flight.setCapacity(flight.getCapacity() - 1);
        flightRepository.save(flight);
    }

    @Override
    @Transactional
    public void cancelBooking(Long id){

        Flight flight = getFlightById(id);
        flight.setCapacity(flight.getCapacity() + 1);
        flightRepository.save(flight);
    }

}
