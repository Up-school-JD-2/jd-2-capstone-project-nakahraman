package com.upschool.airport.app.service.ticket;

import com.upschool.airport.app.dto.ticket.TicketDto;
import com.upschool.airport.app.dto.ticket.TicketSaveRequest;
import com.upschool.airport.app.dto.ticket.TicketUpdateDto;
import com.upschool.airport.app.entity.Ticket;

import java.util.List;

public interface TicketService {

    TicketDto saveTicket(TicketSaveRequest ticketSaveRequest);
    List<TicketDto> getAllTickets();
    TicketDto getTicketByCustomerId(Long id);
    List<TicketDto> getTicketByFlightId(Long id);
    Ticket getTicketById(Long id);
    TicketDto updateTicket(TicketUpdateDto request);
 //   void deleteTicket(Long id);

}
