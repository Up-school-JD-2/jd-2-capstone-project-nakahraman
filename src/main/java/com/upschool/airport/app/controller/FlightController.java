package com.upschool.airport.app.controller;

import com.upschool.airport.app.dto.BaseResponse;
import com.upschool.airport.app.dto.flight.FlightDeleteRequest;
import com.upschool.airport.app.dto.flight.FlightDto;
import com.upschool.airport.app.dto.flight.FlightSaveRequest;
import com.upschool.airport.app.dto.flight.FlightUpdateRequest;
import com.upschool.airport.app.service.flight.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<BaseResponse<FlightDto>> createFlight(
            @Valid @RequestBody FlightSaveRequest flightSaveRequest)
    {
        FlightDto flightDto = flightService.saveFlight(flightSaveRequest);

        var response = BaseResponse.<FlightDto>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(flightDto)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<FlightDto>>> searchAllFlights()
    {
        List<FlightDto> flights = flightService.getAllFlights();

        var response = BaseResponse.<List<FlightDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(flights)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<BaseResponse<FlightDto>> updateFlight(@Valid @RequestBody FlightUpdateRequest request)
    {
        FlightDto flight = flightService.updateFlight(request);

        var response = BaseResponse.<FlightDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flight)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Object>> deleteFlight(@RequestBody FlightDeleteRequest deleteRequest)
    {
        flightService.deleteFlight(deleteRequest.getId());

        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .data("Customer with ID " + deleteRequest.getId() + " deleted successfully!")
                .isSuccess(true)
                .build();

        return ResponseEntity.ok(response);
    }

}
