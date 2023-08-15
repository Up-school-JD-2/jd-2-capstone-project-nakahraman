package com.upschool.airport.app.service.customer;

import com.upschool.airport.app.dto.customer.CustomerDto;
import com.upschool.airport.app.dto.customer.CustomerSaveRequest;
import com.upschool.airport.app.dto.customer.CustomerUpdateRequest;
import com.upschool.airport.app.entity.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDto saveCustomer(CustomerSaveRequest customerSaveRequest);
    CustomerDto getCustomerDtoById(Long id);
    Customer getCustomerById(Long customerId);
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(CustomerUpdateRequest customerUpdateRequest);
    void deleteCustomer(Long id);
}
