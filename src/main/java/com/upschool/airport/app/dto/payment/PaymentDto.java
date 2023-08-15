package com.upschool.airport.app.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private Long id;
    private Long ticketId;
    private String price;
    private String creditCardNumber;
    private String customerName;
    private String pnrNumber;
}
