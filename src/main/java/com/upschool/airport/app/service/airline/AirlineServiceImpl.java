package com.upschool.airport.app.service.airline;


import com.upschool.airport.app.entity.Airline;
import com.upschool.airport.app.exception.DuplicateEntityException;
import com.upschool.airport.app.exception.NoSuchEntityFoundException;
import com.upschool.airport.app.repository.AirlineRepository;
import com.upschool.airport.app.dto.airline.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final AirlineServiceConvertDto convertDto;

    @Override
    @Transactional
    public AirlineDto saveAirline(AirlineSaveRequest request) {

        checkDuplicateAirlineCode(request.getAirlineCode());
        Airline airline = convertDto.airlineSaveRequestToEntity(request);
        return convertDto.entityToAirlineDto(airlineRepository.save(airline));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AirlineDto> getAllAirlines() {

        return getAirlineDtoList(airlineRepository.findAll());
    }

    private List<AirlineDto> getAirlineDtoList(List<Airline> airlines){

        return airlines.stream()
                .map(data -> convertDto.entityToAirlineDto(data))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AirlineDto> getByAirlineName(AirlineSearchNameDto airlineSearchNameDto) {

        List<Airline> airlines = airlineRepository.findByAirlineNameIgnoreCaseContaining(airlineSearchNameDto.getAirlineName());
        return getAirlineDtoList(airlines);
    }

    @Override
    @Transactional(readOnly = true)
    public AirlineDto getAirlineDtoByAirlineCode(AirlineSearchCodeDto airlineCodeDto) {

        Airline airline= airlineRepository.findByAirlineCode(airlineCodeDto.getAirlineCode())
                .orElseThrow(() -> new NoSuchEntityFoundException("Airline with CODE " + airlineCodeDto.getAirlineCode() + " not found!"));
        return convertDto.entityToAirlineDto(airline);
    }

    private void checkDuplicateAirlineCode(String airlineCode){

        if(airlineRepository.existsByAirlineCodeIgnoreCase(airlineCode)){
            throw new DuplicateEntityException("Airline with CODE " + airlineCode + " already exists!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Airline getByAirlineId(Long id) {

        return airlineRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityFoundException("Airline with ID " + id + " not found!"));
    }


    @Override
    @Transactional
    public AirlineDto updateAirline(AirlineUpdateRequest request){

        getByAirlineId(request.getId());
        Airline airline = convertDto.AirlineUpdateRequestToEntity(request);
        return convertDto.entityToAirlineDto(airlineRepository.save(airline));
    }

    @Override
    @Transactional
    public void deleteAirline(Long id) {

        getByAirlineId(id);
        airlineRepository.deleteById(id);
    }
}
