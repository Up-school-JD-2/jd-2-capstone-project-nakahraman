package com.upschool.airport.app.repository;

import com.upschool.airport.app.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport,Long> {

    List<Airport> findByAirportNameIgnoreCaseContaining(String airportName);

    boolean existsByAirportCodeIgnoreCase(String airportCode);
}
