package com.upschool.airport.app.controller;

import com.upschool.airport.app.dto.BaseResponse;
import com.upschool.airport.app.dto.airport.*;
import com.upschool.airport.app.service.airport.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @PostMapping
    public ResponseEntity<BaseResponse<AirportCityDto>> createAirport(
            @Valid @RequestBody AirportSaveRequest airportSaveRequests)
    {
        AirportCityDto savedAirport = airportService.saveAirport(airportSaveRequests);

        var response = BaseResponse.<AirportCityDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(savedAirport)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<AirportCityDto>>> getAllAirports()
    {
        List<AirportCityDto> airports = airportService.getAllAirports();

        var response = BaseResponse.<List<AirportCityDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airports)
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("/search/byName")
    public ResponseEntity<BaseResponse<List<AirportDto>>> getAirports(
            @Valid @RequestBody AirportSearchNameDto airportSearchDto)
    {
        List<AirportDto> airports = airportService.getAirportsByName(airportSearchDto);

        var response = BaseResponse.<List<AirportDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airports)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<BaseResponse<AirportCityDto>> updateAirport(
            @Valid @RequestBody AirportUpdateRequest airportUpdateRequest)
    {
        AirportCityDto updatedAirport = airportService.updateAirport(airportUpdateRequest);

        var response = BaseResponse.<AirportCityDto>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(updatedAirport)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Object>> deleteAirport(@RequestBody AirportDeleteRequest deleteRequest)
    {
        airportService.deleteAirport(deleteRequest.getId());

        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .data("Airport with ID " + deleteRequest.getId() + " deleted successfully!")
                .isSuccess(true)
                .build();

        return ResponseEntity.ok(response);
    }

}
