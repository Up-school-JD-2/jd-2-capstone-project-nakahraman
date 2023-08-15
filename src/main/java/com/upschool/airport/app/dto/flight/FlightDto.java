package com.upschool.airport.app.dto.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightDto {

    private Long id;

    private String flightNumber;

    private int capacity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date boardingDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date arrivalDate;

    private String flightDurationInHours;

    private String airlineName;

    private String route;


}
