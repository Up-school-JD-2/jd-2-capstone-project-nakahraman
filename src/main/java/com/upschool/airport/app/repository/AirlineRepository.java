package com.upschool.airport.app.repository;

import com.upschool.airport.app.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

    List<Airline> findByAirlineNameIgnoreCaseContaining(String airlineName);

    Optional<Airline> findByAirlineCode(String airlineCode);

    boolean existsByAirlineCodeIgnoreCase(String airlineCode);
}
