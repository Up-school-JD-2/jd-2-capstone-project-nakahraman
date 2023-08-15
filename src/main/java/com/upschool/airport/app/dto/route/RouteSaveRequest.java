package com.upschool.airport.app.dto.route;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {

    @NotNull(message = "Departure Airport Id cannot be null!")
    private Long departureAirportId;

    @NotNull(message = "Arrival Airport Id cannot be null!")
    private Long arrivalAirportId;

}
