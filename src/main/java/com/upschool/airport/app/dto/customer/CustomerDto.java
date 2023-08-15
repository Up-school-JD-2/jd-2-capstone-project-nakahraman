package com.upschool.airport.app.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    private String identityNumber;
    private String name;
    private String surname;
    private String phone;
    private String email;

}
