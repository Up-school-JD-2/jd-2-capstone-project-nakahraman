package com.upschool.airport.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Table(name = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE payment SET active = false WHERE id=?")
@Where(clause = "active = true")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "credit_card_number",
            nullable = false,
            length = 16)
    private String creditCardNumber;

    @Column(name = "price",
            nullable = false)
    Double price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payment_date",
            nullable = false)
    private Date paymentDate;

    @Column(name = "PNR_Number",
            nullable = false)
    private String pnrNumber;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

}
