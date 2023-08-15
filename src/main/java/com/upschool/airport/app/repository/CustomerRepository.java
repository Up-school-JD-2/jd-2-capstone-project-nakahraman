package com.upschool.airport.app.repository;

import com.upschool.airport.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByIdentityNumber(String identityNumber);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}
