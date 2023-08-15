package com.upschool.airport.app.service.airport;

import com.upschool.airport.app.dto.airport.*;
import com.upschool.airport.app.entity.Airport;
import com.upschool.airport.app.exception.DuplicateEntityException;
import com.upschool.airport.app.exception.NoSuchEntityFoundException;
import com.upschool.airport.app.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService{

    private final AirportRepository airportRepository;
    private final AirportServiceConvertDto convertDto;

    @Override
    @Transactional
    public AirportCityDto saveAirport(AirportSaveRequest request) {

        checkDuplicateAirportCode(request.getAirportCode());
        var airport = convertDto.airportSaveRequestToEntity(request);
        return convertDto.entityToAirportCityDto(airportRepository.save(airport));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AirportCityDto> getAllAirports(){

       List<Airport> airports =  airportRepository.findAll();
       return airports.stream()
               .map(airport -> convertDto.entityToAirportCityDto(airport))
               .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public AirportCityDto updateAirport(AirportUpdateRequest request) {

        Airport airport = getAirportById(request.getId());
        checkDuplicateAirportCode(request.getAirportCode());
        var airportEntity = convertDto.AirportDtoToEntity(request, airport);
        return convertDto.entityToAirportCityDto(airportRepository.save(airportEntity));
    }

    private void checkDuplicateAirportCode(String airportCode){

        if(airportRepository.existsByAirportCodeIgnoreCase(airportCode)){
            throw new DuplicateEntityException("Airport with CODE " + airportCode + " already exists!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Airport getAirportById(Long id){

        return airportRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityFoundException("Airport with ID " + id + " not found!"));
    }

    @Override
    @Transactional
    public void deleteAirport(Long id) {
        getAirportById(id);
        airportRepository.deleteById(id);
    }

     @Override
     @Transactional(readOnly = true)
     public List<AirportDto> getAirportsByName(AirportSearchNameDto airportSearchDto) {

        List<Airport> airport = airportRepository.findByAirportNameIgnoreCaseContaining(airportSearchDto.getAirportName());
        return airport.stream()
                .map(data -> convertDto.entityToAirportDto(data))
                .collect(Collectors.toList());
     }

}
