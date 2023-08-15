package com.upschool.airport.app.service.payment;

import com.upschool.airport.app.dto.payment.PaymentDto;
import com.upschool.airport.app.dto.payment.PaymentResultDto;
import com.upschool.airport.app.dto.payment.PaymentSaveRequest;
import com.upschool.airport.app.entity.Payment;
import com.upschool.airport.app.exception.CompletedPaymentException;
import com.upschool.airport.app.exception.NoSuchEntityFoundException;
import com.upschool.airport.app.repository.PaymentRepository;
import com.upschool.airport.app.service.flight.FlightService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Builder
public class PaymentServiceImpl implements PaymentService{
    private final PaymentServiceConvertDto convertDto;
    private final PaymentRepository paymentRepository;
    private final FlightService flightService;

    @Override
    @Transactional
    public PaymentResultDto savePayment(PaymentSaveRequest request) {

        validatePaymentNotCompleted(request.getTicketId());

        Payment payment = convertDto.paymentSaveRequestToEntity(request);
        payment = paymentRepository.save(payment);

        return convertDto.entityToPaymentResultDto(payment);
    }

    private void validatePaymentNotCompleted(Long ticketId) {
        if (paymentRepository.findByTicketId(ticketId).isPresent()) {
            throw new CompletedPaymentException("Payment has already been completed for this ticket!");
        }
    }

    private Payment getPaymentById(Long id) {

        return paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityFoundException("Payment with ID " + id + " not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(data -> convertDto.entityToPaymentDto(data))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePayment(Long id){

        Payment payment = getPaymentById(id);
        flightService.cancelBooking(payment.getTicket().getFlight().getId());

        paymentRepository.deleteById(id);
    }

}
