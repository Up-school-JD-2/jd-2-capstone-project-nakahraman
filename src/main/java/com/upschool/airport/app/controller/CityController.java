package com.upschool.airport.app.controller;

import com.upschool.airport.app.dto.BaseResponse;
import com.upschool.airport.app.dto.city.*;
import com.upschool.airport.app.service.city.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping()
    public ResponseEntity<BaseResponse<CityDto>> createCity(
            @Valid @RequestBody CitySaveRequest citySaveRequests)
    {
        CityDto savedCity = cityService.saveCity(citySaveRequests);

        var response = BaseResponse.<CityDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(savedCity)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CityDto>>> getAllCities(){
        List<CityDto> cities = cityService.getAllCities();

        var response = BaseResponse.<List<CityDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(cities)
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/citiesWithAirports")
    public ResponseEntity<BaseResponse<List<CityAirportDto>>> getCitiesWithAirports()
    {
        List<CityAirportDto> allCitiesWithAirports = cityService.getCitiesWithAirports();

        var response = BaseResponse.<List<CityAirportDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(allCitiesWithAirports)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/get/byName")
    public ResponseEntity<BaseResponse<CityAirportDto>> getCityByName(@Valid @RequestBody CitySearchRequest citySearchRequest)
    {
        CityAirportDto cities = cityService.getCityWithAirport(citySearchRequest);

        var response = BaseResponse.<CityAirportDto>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(cities)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<BaseResponse<CityDto>> updateCity(@Valid @RequestBody CityUpdateRequest updateRequest)
    {
        CityDto cityResponseDto = cityService.updateCity(updateRequest);

        var response = BaseResponse.<CityDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(cityResponseDto)
                .build();

        return ResponseEntity.ok(response);
    }


    @DeleteMapping
    public ResponseEntity<BaseResponse<Object>> deleteCity(@RequestBody CityDeleteRequest deleteRequest)
    {
        cityService.deleteCity(deleteRequest.getId());

        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .isSuccess(true)
                .data("City with ID " + deleteRequest.getId() + " deleted successfully!")
                .build();

        return ResponseEntity.ok(response);
    }
}
