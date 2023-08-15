package com.upschool.airport.app.repository;

import com.upschool.airport.app.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {
    Optional<City> findByCityNameIgnoreCase(String cityName);

    boolean existsByCityNameIgnoreCase(String cityName);

}
