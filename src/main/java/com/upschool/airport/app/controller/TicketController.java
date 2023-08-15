package com.upschool.airport.app.controller;

import com.upschool.airport.app.dto.BaseResponse;
import com.upschool.airport.app.dto.ticket.TicketDto;
import com.upschool.airport.app.dto.ticket.TicketSaveRequest;
import com.upschool.airport.app.dto.ticket.TicketUpdateDto;
import com.upschool.airport.app.service.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    @PostMapping
    public ResponseEntity<BaseResponse<TicketDto>> createTicket(@Valid @RequestBody TicketSaveRequest ticketSaveRequest)
    {
        TicketDto ticketDto = ticketService.saveTicket(ticketSaveRequest);

        var response = BaseResponse.<TicketDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketDto)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("search/byCustomerId")
    public ResponseEntity<BaseResponse<TicketDto>> getTicketByCustomerId(@PathVariable Long id){

        TicketDto ticket = ticketService.getTicketByCustomerId(id);

        var response = BaseResponse.<TicketDto>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(ticket)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("search/byFlightId")
    public ResponseEntity<BaseResponse<List<TicketDto>>> getTicketByFlightId(@PathVariable Long id){

        List<TicketDto> tickets = ticketService.getTicketByFlightId(id);

        var response = BaseResponse.<List<TicketDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(tickets)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<TicketDto>>> getAllTickets()
    {
        List<TicketDto> tickets = ticketService.getAllTickets();

        var response = BaseResponse.<List<TicketDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(tickets)
                .build();

        return ResponseEntity.ok(response);

    }

    @PutMapping
    public ResponseEntity<BaseResponse<TicketDto>> updateTicket(@Valid @RequestBody TicketUpdateDto updatedTicket)
    {
        TicketDto ticketDto = ticketService.updateTicket(updatedTicket);
        var response = BaseResponse.<TicketDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketDto)
                .build();

        return ResponseEntity.ok(response);
    }

  /*  @DeleteMapping
    public ResponseEntity<BaseResponse<Object>> deleteTicket(@RequestBody TicketDeleteRequest deleteRequest)
    {
        ticketService.deleteTicket(deleteRequest.getId());

        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .data("Ticket with ID " + deleteRequest.getId() + " deleted successfully!")
                .isSuccess(true)
                .build();

        return ResponseEntity.ok(response);
    }

   */

}
