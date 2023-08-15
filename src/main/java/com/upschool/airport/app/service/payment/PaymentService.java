package com.upschool.airport.app.service.payment;

import com.upschool.airport.app.dto.payment.PaymentDto;
import com.upschool.airport.app.dto.payment.PaymentResultDto;
import com.upschool.airport.app.dto.payment.PaymentSaveRequest;

import java.util.List;

public interface PaymentService {
    PaymentResultDto savePayment(PaymentSaveRequest request);
    void deletePayment(Long id);
    List<PaymentDto> getAllPayments();
}
