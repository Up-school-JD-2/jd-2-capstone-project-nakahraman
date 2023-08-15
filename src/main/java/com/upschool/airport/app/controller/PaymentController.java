package com.upschool.airport.app.controller;

import com.upschool.airport.app.dto.BaseResponse;
import com.upschool.airport.app.dto.payment.PaymentDeleteRequest;
import com.upschool.airport.app.dto.payment.PaymentDto;
import com.upschool.airport.app.dto.payment.PaymentResultDto;
import com.upschool.airport.app.dto.payment.PaymentSaveRequest;
import com.upschool.airport.app.service.payment.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    @PostMapping
    public ResponseEntity<BaseResponse<PaymentResultDto>> createPayment(
            @Valid @RequestBody PaymentSaveRequest paymentSaveRequest)
    {
        PaymentResultDto paymentDto = paymentService.savePayment(paymentSaveRequest);

        var response = BaseResponse.<PaymentResultDto>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(paymentDto)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<PaymentDto>>> getAllPayments()
    {
        List<PaymentDto> allPayments = paymentService.getAllPayments();

        var response = BaseResponse.<List<PaymentDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(allPayments)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Object>> deletePayment(@RequestBody PaymentDeleteRequest deleteRequest)
    {
        paymentService.deletePayment(deleteRequest.getId());

        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .data("Payment with ID " + deleteRequest.getId() + " deleted successfully!")
                .isSuccess(true)
                .build();

        return ResponseEntity.ok(response);
    }
}
