package com.upschool.airport.app.repository;

import com.upschool.airport.app.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,Long> {
    boolean existsByFromAirportIdAndToAirportId(Long fromAirportId, Long toAirportId);
}
