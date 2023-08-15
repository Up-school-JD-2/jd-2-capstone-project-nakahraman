package com.upschool.airport.app.service.customer;

import com.upschool.airport.app.dto.customer.CustomerDto;
import com.upschool.airport.app.dto.customer.CustomerSaveRequest;
import com.upschool.airport.app.dto.customer.CustomerUpdateRequest;
import com.upschool.airport.app.entity.Customer;
import com.upschool.airport.app.exception.NoSuchEntityFoundException;
import com.upschool.airport.app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerServiceConvertDto convertDto;
    private final CustomerRepository customerRepository;
    private final DuplicateValueChecker duplicateValueChecker;

    @Override
    @Transactional
    public CustomerDto saveCustomer(CustomerSaveRequest request) {

        duplicateValueChecker.checkDuplicateValues(request.getIdentityNumber(), request.getEmail(), request.getPhone());
        Customer customer = convertDto.customerSaveRequestToEntity(request);
        return convertDto.entityToCustomerDto(customerRepository.save(customer));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerDtoById(Long id){
        Customer customer = getCustomerById(id);
        return convertDto.entityToCustomerDto(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerById(Long customerId) {

        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchEntityFoundException("Customer with ID " + customerId + " not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(data -> convertDto.entityToCustomerDto(data))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public CustomerDto updateCustomer(CustomerUpdateRequest request) {

        getCustomerById(request.getId());
        duplicateValueChecker.checkDuplicateValues(request.getIdentityNumber(), request.getEmail(), request.getPhone());
        Customer customer = convertDto.updateRequestToEntity(request);
        return convertDto.entityToCustomerDto(customerRepository.save(customer));
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {

        getCustomerById(id);
        customerRepository.deleteById(id);
    }
}
