package com.upschool.airport.app.service.flight;

import com.upschool.airport.app.dto.flight.FlightDto;
import com.upschool.airport.app.dto.flight.FlightSaveRequest;
import com.upschool.airport.app.dto.flight.FlightUpdateRequest;
import com.upschool.airport.app.entity.Airline;
import com.upschool.airport.app.entity.Flight;
import com.upschool.airport.app.entity.Route;
import com.upschool.airport.app.service.airline.AirlineService;
import com.upschool.airport.app.service.route.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class FlightServiceConvertDto {

    private final AirlineService airlineService;

    private final RouteService routeService;

    public Flight flightSaveRequestToEntity(FlightSaveRequest request) {

        Airline airline = airlineService.getByAirlineId(request.getAirlineId());
        Route route = routeService.getRouteById(request.getRouteId());
        Date arrivalDate = calculateArrivalDate(request.getBoardingDate(),request.getFlightDurationInMinutes());


        return Flight.builder()
                .flightNumber(airline.getAirlineCode().toUpperCase() + route.getId())
                .boardingDate(request.getBoardingDate())
                .arrivalDate(arrivalDate)
                .flightDurationInMinutes(request.getFlightDurationInMinutes())
                .capacity(request.getCapacity())
                .airline(airline)
                .route(route)
                .build();
    }

    private Date calculateArrivalDate(Date boardingDate, Integer flightDurationInMinutes) {

        Instant departureInstant = boardingDate.toInstant();
        Duration duration = Duration.ofMinutes(flightDurationInMinutes);
        Instant arrivalInstant = departureInstant.plus(duration);
        return Date.from(arrivalInstant);

    }

    public String convertMinutesToHours(Integer flightDurationInMinutes){

        Duration duration = Duration.ofMinutes(flightDurationInMinutes);
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        return hours + " h(s) " + minutes+ " min(s)";
    }


    public FlightDto entityToFlightDto(Flight flight) {

        StringBuffer route = new StringBuffer();
        route.append(flight.getRoute().getFromAirport().getAirportCode())
                .append(" ")
                .append(flight.getRoute().getFromAirport().getAirportName())
                .append(" - ")
                .append(flight.getRoute().getToAirport().getAirportCode())
                .append(" ")
                .append(flight.getRoute().getToAirport().getAirportName());

        Date arrivalDate =
                calculateArrivalDate(flight.getBoardingDate(), flight.getFlightDurationInMinutes());

        return FlightDto.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .boardingDate(flight.getBoardingDate())
                .arrivalDate(arrivalDate)
                .flightDurationInHours(convertMinutesToHours(flight.getFlightDurationInMinutes()))
                .capacity(flight.getCapacity())
                .airlineName(flight.getAirline().getAirlineName())
                .route(route.toString())
                .build();
    }


    public Flight updateRequestToEntity(FlightUpdateRequest request) {

        Airline airline = airlineService.getByAirlineId(request.getAirlineId());
        Route route = routeService.getRouteById(request.getRouteId());
        Date arrivalDate = calculateArrivalDate(request.getBoardingDate(),request.getFlightDurationInMinutes());

        return Flight.builder()
                .id(request.getId())
                .flightNumber(airline.getAirlineCode() + route.getId())
                .boardingDate(request.getBoardingDate())
                .arrivalDate(arrivalDate)
                .flightDurationInMinutes(request.getFlightDurationInMinutes())
                .capacity(request.getCapacity())
                .airline(airline)
                .route(route)
                .build();
    }
}
