package com.upschool.airport.app.controller;

import com.upschool.airport.app.dto.BaseResponse;
import com.upschool.airport.app.service.airline.AirlineService;
import com.upschool.airport.app.dto.airline.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;

    @PostMapping
    public ResponseEntity<BaseResponse<AirlineDto>> createAirline(
            @Valid @RequestBody AirlineSaveRequest airlineSaveRequest)
    {
        AirlineDto savedAirline = airlineService.saveAirline(airlineSaveRequest);
        var response = BaseResponse.<AirlineDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(savedAirline)
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<BaseResponse<List<AirlineDto>>> getAllAirlines() {
        List<AirlineDto> airlines = airlineService.getAllAirlines();

        var response = BaseResponse.<List<AirlineDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airlines)
                .build();

        return ResponseEntity.ok(response);
    }


    @PostMapping("/get/byName")
    public ResponseEntity<BaseResponse<List<AirlineDto>>> getAirlineByName(
            @Valid @RequestBody AirlineSearchNameDto airlineSearchNameDto)
    {
        List<AirlineDto> airlines = airlineService.getByAirlineName(airlineSearchNameDto);
          BaseResponse<List<AirlineDto>> response = BaseResponse.<List<AirlineDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airlines)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/get/byCode")
    public ResponseEntity<BaseResponse<AirlineDto>> getAirlineByCode(
            @Valid @RequestBody AirlineSearchCodeDto airlineCodeDto)
    {
        AirlineDto airline = airlineService.getAirlineDtoByAirlineCode(airlineCodeDto);
        var response = BaseResponse.<AirlineDto>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airline)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<BaseResponse<AirlineDto>> updateAirline(@Valid @RequestBody AirlineUpdateRequest airlineUpdateRequest)
    {
        AirlineDto updatedAirline =airlineService.updateAirline(airlineUpdateRequest);
        var response = BaseResponse.<AirlineDto>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(updatedAirline)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Object>> deleteAirline(@RequestBody AirlineDeleteRequest deleteRequest)
    {
        airlineService.deleteAirline(deleteRequest.getId());

        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .data("Airline with ID " + deleteRequest.getId() + " deleted successfully!")
                .isSuccess(true)
                .build();

        return ResponseEntity.ok(response);
    }

}
