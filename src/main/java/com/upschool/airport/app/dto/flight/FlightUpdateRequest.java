package com.upschool.airport.app.dto.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightUpdateRequest {


    @NotNull(message = "Flight Id cannot be null!")
    private long id;

    @NotNull(message = "Capacity cannot be null!")
    @Positive
    private Integer capacity;

    @NotNull(message = "Boarding Date cannot be null!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Future
    private Date boardingDate;

    @NotNull(message = "Boarding Date cannot be null!")
    @Positive
    private Integer flightDurationInMinutes;

    @NotNull(message = "Airline Id cannot be null!")
    private Long airlineId;

    @NotNull(message = "Route Id cannot be null!")
    private Long routeId;
}
