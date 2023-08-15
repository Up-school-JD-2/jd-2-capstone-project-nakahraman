package com.upschool.airport.app.service.customer;

import com.upschool.airport.app.exception.DuplicateEntityException;
import com.upschool.airport.app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DuplicateValueChecker {

    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public void checkDuplicateValues(String identityNumber, String email, String phone) {

        checkDuplicateIdentityNumber(identityNumber);
        checkDuplicateEmail(email);
        checkDuplicatePhone(phone);
    }

    private void checkDuplicateIdentityNumber(String identityNumber) {

        if (customerRepository.existsByIdentityNumber(identityNumber)) {
            throw new DuplicateEntityException("Customer with IDENTITY NUMBER " + identityNumber + " already exists!");
        }
    }

    private void checkDuplicateEmail(String email) {

        if (StringUtils.isNotBlank(email) && customerRepository.existsByEmail(email)) {
            throw new DuplicateEntityException("Customer with EMAIL " + email + " already exists!");
        }
    }

    private void checkDuplicatePhone(String phone) {

        if (StringUtils.isNotBlank(phone) && customerRepository.existsByPhone(phone)) {
            throw new DuplicateEntityException("Customer with PHONE " + phone + " already exists!");
        }
    }
}
