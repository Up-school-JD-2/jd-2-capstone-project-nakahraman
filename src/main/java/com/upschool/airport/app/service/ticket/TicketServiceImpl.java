package com.upschool.airport.app.service.ticket;

import com.upschool.airport.app.dto.ticket.TicketDto;
import com.upschool.airport.app.dto.ticket.TicketSaveRequest;
import com.upschool.airport.app.dto.ticket.TicketUpdateDto;
import com.upschool.airport.app.entity.Flight;
import com.upschool.airport.app.entity.Ticket;
import com.upschool.airport.app.exception.CustomerHasTicketForThisFlight;
import com.upschool.airport.app.exception.FlightCapacityExceededException;
import com.upschool.airport.app.exception.NoSuchEntityFoundException;
import com.upschool.airport.app.repository.TicketRepository;
import com.upschool.airport.app.service.flight.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketServiceConvertDto convertDto;
    private final TicketRepository ticketRepository;
    private final FlightService flightService;

    @Override
    @Transactional
    public TicketDto saveTicket(TicketSaveRequest request){

        checkPrerequisites(request.getCustomerId(), request.getFlightId());

        Ticket ticket = convertDto.ticketSaveRequestToEntity(request);
        flightService.bookSeat(ticket.getFlight().getId());

        return convertDto.entityToTicketDto(ticketRepository.save(ticket));
    }

    private Flight checkPrerequisites(Long customerId, Long flightId) {

        checkDuplicateCustomerAndFlight(customerId, flightId);

        Flight flight = flightService.getFlightById(flightId);
        validateFlightCapacity(flight);

        return flight;
    }

    private void checkDuplicateCustomerAndFlight(Long customerId, Long flightId){

        if(ticketRepository.existsByCustomerIdAndFlightId(customerId, flightId)){
            throw new CustomerHasTicketForThisFlight("Ticket with" +
                                                     " CUSTOMER ID: " + customerId +
                                                     " and FLIGHT ID: " + flightId +
                                                     " already exists!");
        }
    }

    private void validateFlightCapacity(Flight flight) {
        if (flight.getCapacity() == 0) {
            throw new FlightCapacityExceededException("Flight capacity is full for the" +
                                                      " FLIGHT ID: " + flight.getId() +
                                                      " FLIGHT NUMBER: " + flight.getFlightNumber() +
                                                      " AIRLINE: " + flight.getAirline().getAirlineName() );
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketDto> getAllTickets() {

        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(data -> convertDto.entityToTicketDto(data))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TicketDto updateTicket(TicketUpdateDto request) {

        Ticket ticket = getTicketById(request.getId());
        flightService.cancelBooking(ticket.getFlight().getId());

        checkPrerequisites(request.getCustomerId(), request.getFlightId());
        ticket = convertDto.ticketUpdateRequestToEntity(request);
        ticketRepository.save(ticket);
        flightService.bookSeat(ticket.getFlight().getId());
        return convertDto.entityToTicketDto(ticket);
    }


 /*   @Override
    @Transactional
    public void deleteTicket(Long id) {

        getTicketById(id);
        Flight flight = flightService.findFlightByTicketId(id);
        flightService.cancelBooking(flight.getId());
        ticketRepository.deleteById(id);
    }

  */

    @Override
    @Transactional(readOnly = true)
    public Ticket getTicketById(Long id) {

        return ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityFoundException("Ticket with ID " + id + " not found!"));
    }


    @Override
    @Transactional(readOnly = true)
    public TicketDto getTicketByCustomerId(Long id) {

        Ticket ticket = ticketRepository.findByCustomerId(id)
                .orElseThrow(() -> new NoSuchEntityFoundException("Ticket with ID " + id + " not found!"));
        return convertDto.entityToTicketDto(ticket);
    }


    @Override
    @Transactional(readOnly = true)
    public List<TicketDto> getTicketByFlightId(Long id) {

        List<Ticket> tickets = ticketRepository.findByFlightId(id).orElseThrow(() -> new NoSuchEntityFoundException("Ticket with ID " + id + " not found!"));
        return tickets.stream()
                .map(data -> convertDto.entityToTicketDto(data))
                .collect(Collectors.toList());

    }

}

