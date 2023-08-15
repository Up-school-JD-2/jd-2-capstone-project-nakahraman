package com.upschool.airport.app.repository;

import com.upschool.airport.app.entity.Flight;
import com.upschool.airport.app.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByCustomerId(Long id);

    Optional<List<Ticket>> findByFlightId(Long id);

    @Query(value = "SELECT t.flight " +
           "FROM Ticket t " +
           "WHERE t.id = :ticketId")
    Flight findFlightByTicketId(@Param("ticketId") Long ticketId);

    boolean existsByCustomerIdAndFlightId(Long customerId, Long flightId);
}
