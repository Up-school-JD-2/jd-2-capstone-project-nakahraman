package com.upschool.airport.app.service.route;


import com.upschool.airport.app.dto.route.RouteDto;
import com.upschool.airport.app.dto.route.RouteSaveRequest;
import com.upschool.airport.app.dto.route.RouteUpdateDto;
import com.upschool.airport.app.entity.Route;

import java.util.List;

public interface RouteService {

    public RouteDto saveRoute(RouteSaveRequest RouteSaveRequest);
    List<RouteDto> getAllRoutes();
    public RouteDto updateRoute(RouteUpdateDto request);
    void deleteRoute(Long id);
    Route getRouteById(Long id);

}
