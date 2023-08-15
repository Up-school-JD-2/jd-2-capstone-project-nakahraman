package com.upschool.airport.app.controller;

import com.upschool.airport.app.dto.BaseResponse;
import com.upschool.airport.app.dto.customer.CustomerDeleteRequest;
import com.upschool.airport.app.dto.customer.CustomerDto;
import com.upschool.airport.app.dto.customer.CustomerSaveRequest;
import com.upschool.airport.app.dto.customer.CustomerUpdateRequest;
import com.upschool.airport.app.service.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<BaseResponse<CustomerDto>> createCustomer(
            @Valid @RequestBody CustomerSaveRequest customerSaveRequest)
    {
        CustomerDto savedCustomer = customerService.saveCustomer(customerSaveRequest);

        var response = BaseResponse.<CustomerDto>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(savedCustomer)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CustomerDto>>> getAllCustomers(){

        List<CustomerDto> customers = customerService.getAllCustomers();

        var response = BaseResponse.<List<CustomerDto>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(customers)
                .build();

        return ResponseEntity.ok(response);
    }


    @PutMapping
    public ResponseEntity<BaseResponse<CustomerDto>> updateCustomer(
            @Valid @RequestBody CustomerUpdateRequest customerUpdateRequest)
    {
        CustomerDto updatedCustomer =customerService.updateCustomer(customerUpdateRequest);

        var response = BaseResponse.<CustomerDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(updatedCustomer)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Object>> deleteCustomer(@RequestBody CustomerDeleteRequest deleteRequest)
    {
        customerService.deleteCustomer(deleteRequest.getId());

        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .data("Customer with ID " + deleteRequest.getId() + " deleted successfully!")
                .isSuccess(true)
                .build();

        return ResponseEntity.ok(response);
    }

}
