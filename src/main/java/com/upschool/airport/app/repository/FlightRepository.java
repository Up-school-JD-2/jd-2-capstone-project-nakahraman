package com.upschool.airport.app.repository;

import com.upschool.airport.app.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    boolean existsByAirlineIdAndRouteId(Long airlineId, Long routeId);

}
