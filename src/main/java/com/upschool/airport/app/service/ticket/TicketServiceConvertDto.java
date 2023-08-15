package com.upschool.airport.app.service.ticket;

import com.upschool.airport.app.dto.ticket.TicketDto;
import com.upschool.airport.app.dto.ticket.TicketSaveRequest;
import com.upschool.airport.app.dto.ticket.TicketUpdateDto;
import com.upschool.airport.app.entity.Customer;
import com.upschool.airport.app.entity.Flight;
import com.upschool.airport.app.entity.Ticket;
import com.upschool.airport.app.service.customer.CustomerService;
import com.upschool.airport.app.service.flight.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TicketServiceConvertDto {

    private final CustomerService customerService;
    private final FlightService flightService;
    public Ticket ticketSaveRequestToEntity(TicketSaveRequest request) {

        Customer customer = customerService.getCustomerById(request.getCustomerId());
        Flight flight = flightService.getFlightById(request.getFlightId());

        return Ticket.builder()
                .customer(customer)
                .flight(flight)
                .build();
    }

    public TicketDto entityToTicketDto(Ticket ticket) {

        return TicketDto.builder()
                .ticketId(ticket.getId())
                .customerName(ticket.getCustomer().getName() + " " + ticket.getCustomer().getSurname())
                .flightNumber(ticket.getFlight().getFlightNumber())
                .airline(ticket.getFlight().getAirline().getAirlineName())
                .departureAirport(ticket.getFlight().getRoute().getFromAirport().getAirportName())
                .arrivalAirport(ticket.getFlight().getRoute().getToAirport().getAirportName())
                .boardingDate(ticket.getFlight().getBoardingDate())
                .arrivalDate(ticket.getFlight().getArrivalDate())
                .flightDuration(convertMinutesToHours(ticket.getFlight().getFlightDurationInMinutes()))
                .build();
    }

    public Ticket ticketUpdateRequestToEntity(TicketUpdateDto request) {

        Customer customer = customerService.getCustomerById(request.getCustomerId());
        Flight flight = flightService.getFlightById(request.getFlightId());

        return Ticket.builder()
                .id(request.getId())
                .customer(customer)
                .flight(flight)
                .build();
    }

    private String convertMinutesToHours(Integer flightDurationInMinutes){

        Duration duration = Duration.ofMinutes(flightDurationInMinutes);
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        return hours + " h(s) " + minutes+ " min(s)";
    }

}
