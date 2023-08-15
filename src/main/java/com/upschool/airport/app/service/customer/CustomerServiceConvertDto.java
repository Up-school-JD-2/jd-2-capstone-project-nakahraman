package com.upschool.airport.app.service.customer;

import com.upschool.airport.app.dto.customer.CustomerDto;
import com.upschool.airport.app.dto.customer.CustomerSaveRequest;
import com.upschool.airport.app.dto.customer.CustomerUpdateRequest;
import com.upschool.airport.app.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceConvertDto {

    public CustomerDto entityToCustomerDto(Customer customer) {

        return CustomerDto.builder()
                .id(customer.getId())
                .identityNumber(customer.getIdentityNumber())
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }

    public Customer customerSaveRequestToEntity(CustomerSaveRequest request) {

        return Customer.builder()
                .identityNumber(request.getIdentityNumber())
                .name(request.getName())
                .surname(request.getSurname())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();

    }

    public Customer updateRequestToEntity(CustomerUpdateRequest request){

        return Customer.builder()
                .id(request.getId())
                .identityNumber(request.getIdentityNumber())
                .name(request.getName())
                .surname(request.getSurname())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
    }

}
