package com.upschool.airport.app.service.route;


import com.upschool.airport.app.dto.route.RouteDto;
import com.upschool.airport.app.dto.route.RouteSaveRequest;
import com.upschool.airport.app.dto.route.RouteUpdateDto;
import com.upschool.airport.app.entity.Airport;
import com.upschool.airport.app.entity.Route;
import com.upschool.airport.app.exception.DuplicateEntityException;
import com.upschool.airport.app.exception.IdenticalDepartureAndArrivalAirports;
import com.upschool.airport.app.exception.NoSuchEntityFoundException;
import com.upschool.airport.app.repository.RouteRepository;
import com.upschool.airport.app.service.airport.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService{

    private final RouteRepository routeRepository;
    private final RouteServiceConvertDto convertDto;
    private final AirportService airportService;

    @Override
    @Transactional
    public RouteDto saveRoute(RouteSaveRequest request) {
        checkIdenticalAirports(request.getDepartureAirportId(), request.getArrivalAirportId());
        checkDuplicateRoute(request.getDepartureAirportId(), request.getArrivalAirportId());
        Route route = convertDto.routeSaveRequestToEntity(request);
        return convertDto.entityToRouteDto(routeRepository.save(route));
    }


    private void checkIdenticalAirports(Long departureId, Long arrivalId){

        if(departureId.equals(arrivalId))
            throw new IdenticalDepartureAndArrivalAirports("Departure Airport cannot be identical with Arrival Airport!");
    }

    @Transactional(readOnly = true)
    private void checkDuplicateRoute(Long departureId, Long arrivalId){

        if(routeRepository.existsByFromAirportIdAndToAirportId(departureId, arrivalId)){

            Airport departure = airportService.getAirportById(departureId);
            Airport arrival = airportService.getAirportById(arrivalId);

            StringBuffer route = new StringBuffer();
            route.append(departure.getAirportCode())
                    .append(" ")
                    .append(departure.getAirportName())
                    .append(" - ")
                    .append(arrival.getAirportCode())
                    .append(" ")
                    .append(arrival.getAirportName());

            throw new DuplicateEntityException("ROUTE " + route + " already exists!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<RouteDto> getAllRoutes() {
        List<Route> routes = routeRepository.findAll();
        return routes.stream()
                .map(route -> convertDto.entityToRouteDto(route))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RouteDto updateRoute(RouteUpdateDto request) {

        checkIdenticalAirports(request.getDepartureAirportId(), request.getArrivalAirportId());
        getRouteById(request.getId());
        checkDuplicateRoute(request.getDepartureAirportId(), request.getArrivalAirportId());
        Route route = convertDto.updateRequestToEntity(request);
        return convertDto.entityToRouteDto(routeRepository.save(route));
    }

    @Override
    @Transactional(readOnly = true)
    public Route getRouteById(Long id){

        return routeRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityFoundException("Route with ID " + id + " not found!"));
    }

    @Override
    @Transactional
    public void deleteRoute(Long id) {

        getRouteById(id);
        routeRepository.deleteById(id);
    }

}
