package com.upschool.airport.app.controller;


import com.upschool.airport.app.dto.BaseResponse;
import com.upschool.airport.app.dto.route.RouteDeleteRequest;
import com.upschool.airport.app.dto.route.RouteDto;
import com.upschool.airport.app.dto.route.RouteSaveRequest;
import com.upschool.airport.app.dto.route.RouteUpdateDto;
import com.upschool.airport.app.service.route.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<BaseResponse<RouteDto>> createRoute(@Valid @RequestBody RouteSaveRequest routeSaveRequest)
    {
        RouteDto routeDto = routeService.saveRoute(routeSaveRequest);

        var response = BaseResponse.<RouteDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(routeDto)
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<BaseResponse<List<RouteDto>>> getAllRoutes()
    {
        List<RouteDto> routes = routeService.getAllRoutes();

        var response = BaseResponse.<List<RouteDto>>builder()
            .status(HttpStatus.OK.value())
            .isSuccess(true)
            .data(routes)
            .build();

        return ResponseEntity.ok(response);

    }

    @PutMapping
    public ResponseEntity<BaseResponse<RouteDto>> updateRoute(@Valid @RequestBody RouteUpdateDto updatedRoute)
    {
        RouteDto routeDto = routeService.updateRoute(updatedRoute);
        var response = BaseResponse.<RouteDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(routeDto)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Object>> deleteRoute(@RequestBody RouteDeleteRequest deleteRequest)
    {
        routeService.deleteRoute(deleteRequest.getId());

        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .data("Route with ID " + deleteRequest.getId() + " deleted successfully!")
                .isSuccess(true)
                .build();

        return ResponseEntity.ok(response);
    }
}