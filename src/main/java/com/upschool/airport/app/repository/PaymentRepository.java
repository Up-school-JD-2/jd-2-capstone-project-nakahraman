package com.upschool.airport.app.repository;

import com.upschool.airport.app.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTicketId(Long ticketId);

}
