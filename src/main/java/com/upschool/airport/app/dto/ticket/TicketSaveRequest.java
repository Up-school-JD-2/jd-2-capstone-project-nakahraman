package com.upschool.airport.app.dto.ticket;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveRequest {

    @NotNull(message = "Flight Id cannot be null!")
    private Long flightId;
    @NotNull(message = "Customer Id cannot be null!")
    private Long customerId;
}
