package com.upschool.airport.app.service.city;

import com.upschool.airport.app.dto.city.*;
import com.upschool.airport.app.entity.City;
import com.upschool.airport.app.exception.DuplicateEntityException;
import com.upschool.airport.app.exception.NoSuchEntityFoundException;
import com.upschool.airport.app.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{
    private final CityRepository cityRepository;
    private final CityServiceConvertDto convertDto;

    @Override
    @Transactional
    public CityDto saveCity(CitySaveRequest request) {

        checkDuplicateCityName(request.getCityName());
        City city = convertDto.citySaveRequestToEntity(request);
        return convertDto.entityToCityDto(cityRepository.save(city));
    }

    @Override
    @Transactional
    public CityDto updateCity(CityUpdateRequest request) {

        getCityById(request.getId());
        checkDuplicateCityName(request.getCityName());
        City city = convertDto.updateRequestToEntity(request);
        return convertDto.entityToCityDto(cityRepository.save(city));
    }

    private void checkDuplicateCityName(String cityName){

        if(cityRepository.existsByCityNameIgnoreCase(cityName)){
            throw new DuplicateEntityException("CITY NAME " + cityName + " already exists!");
        }
    }

    private City getCityById(Long id){

        return cityRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityFoundException("City with ID " + id + " not found!"));

    }

    @Override
    @Transactional(readOnly = true)
    public List<CityDto> getAllCities(){

              List<City> cities = cityRepository.findAll();
              return cities.stream()
                      .map(city -> convertDto.entityToCityDto(city))
                      .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityAirportDto> getCitiesWithAirports() {
        List<City> all = cityRepository.findAll();
        return all.stream()
               .map(data -> convertDto.entityToCityAirportDto(data)
               ).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CityAirportDto getCityWithAirport(CitySearchRequest citySearchRequest){
        City city = getByCityName(citySearchRequest.getCityName());
        return convertDto.entityToCityAirportDto(city);
    }

    @Override
    @Transactional(readOnly = true)
    public City getByCityName(String cityName){

        return cityRepository
                .findByCityNameIgnoreCase(cityName)
                .orElseThrow(() -> new NoSuchEntityFoundException("City with NAME " + cityName + " not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public City getByCityId(Long id) {
        return cityRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchEntityFoundException("City with ID " + id + " not found!"));
    }


    @Override
    @Transactional
    public void deleteCity(Long id) {

        getCityById(id);
        cityRepository.deleteById(id);
    }
}
