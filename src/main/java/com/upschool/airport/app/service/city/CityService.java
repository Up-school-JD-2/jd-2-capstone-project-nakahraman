package com.upschool.airport.app.service.city;

import com.upschool.airport.app.dto.city.*;
import com.upschool.airport.app.entity.City;

import java.util.List;

public interface CityService {
    CityDto saveCity(CitySaveRequest citySaveRequest);
    List<CityDto> getAllCities();
    List<CityAirportDto> getCitiesWithAirports();
    City getByCityName(String cityName);
    City getByCityId(Long cityId);
    CityDto updateCity(CityUpdateRequest request);
    void deleteCity(Long id);
    CityAirportDto getCityWithAirport(CitySearchRequest citySearchRequest);
}
