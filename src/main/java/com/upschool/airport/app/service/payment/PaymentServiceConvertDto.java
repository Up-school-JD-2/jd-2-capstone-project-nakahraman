package com.upschool.airport.app.service.payment;


import com.upschool.airport.app.dto.payment.PaymentDto;
import com.upschool.airport.app.dto.payment.PaymentResultDto;
import com.upschool.airport.app.dto.payment.PaymentSaveRequest;
import com.upschool.airport.app.entity.Payment;
import com.upschool.airport.app.service.flight.FlightServiceConvertDto;
import com.upschool.airport.app.service.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PaymentServiceConvertDto {

    private final TicketService ticketService;
    private final FlightServiceConvertDto flightConvertDto;

    @Transactional(readOnly = true)
    public Payment paymentSaveRequestToEntity(PaymentSaveRequest request){

        return Payment.builder()
                .creditCardNumber(maskData(removeCharacters(request.getCreditCardNumber())))
                .price(request.getPrice())
                .paymentDate(new Date())
                .pnrNumber(RandomStringUtils.randomAlphanumeric(6))
                .ticket(ticketService.getTicketById(request.getTicketId()))
                .build();
    }

    public PaymentDto entityToPaymentDto(Payment payment){
        return PaymentDto.builder()
                .id(payment.getId())
                .ticketId(payment.getTicket().getId())
                .customerName(payment.getTicket().getCustomer().getName() + " " +
                              payment.getTicket().getCustomer().getSurname())
                .creditCardNumber(payment.getCreditCardNumber())
                .price(payment.getPrice().toString() + " TL")
                .pnrNumber(payment.getPnrNumber())
                .build();
    }

    public PaymentResultDto entityToPaymentResultDto(Payment payment){

        String paymentId = getPaymentId(payment);
        String customerName = getCustomerName(payment);
        String creditCardNumber = maskData(removeCharacters(getCreditCard(payment)));
        String pnrNumber = getPnrNumber(payment);
        String airlineCompany = getAirlineCompany(payment);
        String from = getDepartureAirport(payment);
        String to = getArrivalAirport(payment);
        String boardingDate = getBoardingDate(payment);
        String arrivalDate = getArrivalDate(payment);
        String flightDuration = getFlightDuration(payment);
        String price = getPrice(payment);
        String paymentDate = getPaymentDate(payment);
        String message = buildMessage(customerName, paymentId, creditCardNumber, pnrNumber, airlineCompany,
                from, to, boardingDate,arrivalDate, flightDuration, price, paymentDate);

       return PaymentResultDto.builder()
                .id(payment.getId())
                .message(message)
                .build();
    }

    private String buildMessage(String... components) {
        return "Dear " + components[0] + "\n" +
               "You successfully completed the purchasing the ticket with the following details!\n" +
               String.join(System.lineSeparator(), Arrays.copyOfRange(components, 1, components.length));
    }

    private String getCustomerName(Payment payment) {
        return payment.getTicket().getCustomer().getName() + " " +
               payment.getTicket().getCustomer().getSurname();
    }

    private String getPaymentId(Payment payment) {
        return "Payment Id: " +
               payment.getId().toString();
    }

    private String getCreditCard(Payment payment) {
        return "Credit Card Number: " +
               maskData(removeCharacters(payment.getCreditCardNumber()));
    }

    private String getPnrNumber(Payment payment) {
        return "PNR Number: " +
               payment.getPnrNumber();
    }

    private String getAirlineCompany(Payment payment) {
        return "Airline Company: " +
               payment.getTicket().getFlight().getAirline().getAirlineCode() + "-" +
               payment.getTicket().getFlight().getAirline().getAirlineName();
    }

    private String getDepartureAirport(Payment payment) {
        return "Departure Airport: " +
               payment.getTicket().getFlight().getRoute().getFromAirport().getAirportCode() + "-" +
               payment.getTicket().getFlight().getRoute().getFromAirport().getAirportName();
    }

    private String getBoardingDate(Payment payment) {
        return "Boarding Date: " +
               payment.getTicket().getFlight().getBoardingDate().toString();
    }

    private String getArrivalDate(Payment payment) {
        return "Arrival Date: " +
               payment.getTicket().getFlight().getArrivalDate().toString();
    }

    private String getArrivalAirport(Payment payment) {
        return "Arrival Airport: " +
               payment.getTicket().getFlight().getRoute().getToAirport().getAirportCode() + "-" +
               payment.getTicket().getFlight().getRoute().getToAirport().getAirportName();
    }

    private String getFlightDuration(Payment payment) {
        return "Flight Duration: " +
               flightConvertDto.convertMinutesToHours(payment.getTicket().getFlight().getFlightDurationInMinutes());
    }

    private String getPrice(Payment payment) {
        return "Price: " +
               payment.getPrice() + " TL";
    }

    private String getPaymentDate(Payment payment) {
        return "Payment Date: " +
               payment.getPaymentDate().toString();
    }

    private String removeCharacters(String cardNumber){
        return  cardNumber.replaceAll("[^0-9]", "");
    }

    private String maskData(String cardNumber) {
        return cardNumber.replaceAll(".(?=.{4})", "*");
    }
}
