package com.upschool.airport.app.service.route;

import com.upschool.airport.app.dto.route.RouteDto;
import com.upschool.airport.app.dto.route.RouteSaveRequest;
import com.upschool.airport.app.dto.route.RouteUpdateDto;
import com.upschool.airport.app.entity.Airport;
import com.upschool.airport.app.entity.Route;
import com.upschool.airport.app.service.airport.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteServiceConvertDto {

    public final AirportService airportService;

    public RouteDto entityToRouteDto(Route route){

        return RouteDto.builder()
                .id(route.getId())
                .departureAirport(route.getFromAirport().getAirportName())
                .arrivalAirport(route.getToAirport().getAirportName())
                .build();
    }


    public Route routeSaveRequestToEntity(RouteSaveRequest routeSaveRequest){

        Airport from = airportService.getAirportById(routeSaveRequest.getDepartureAirportId());
        Airport to = airportService.getAirportById(routeSaveRequest.getArrivalAirportId());

        return Route.builder()
                .fromAirport(from)
                .toAirport(to)
                .build();
    }

    public Route updateRequestToEntity(RouteUpdateDto routeUpdateDto){

        Airport from = airportService.getAirportById(routeUpdateDto.getDepartureAirportId());
        Airport to = airportService.getAirportById(routeUpdateDto.getArrivalAirportId());

        return Route.builder()
                .id(routeUpdateDto.getId())
                .fromAirport(from)
                .toAirport(to)
                .build();
    }

}
