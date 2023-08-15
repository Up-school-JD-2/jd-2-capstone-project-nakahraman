package com.upschool.airport.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "flight")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE flight SET active = false WHERE id=?")
@Where(clause = "active = true")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number",
            nullable = false,
            unique = true)
    private String flightNumber;

    @Column(name = "flight_capacity",
            nullable = false)
    private Integer capacity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "boarding_date",
            nullable = false)
    private Date boardingDate;

    @Column(name = "arrival_date",
            nullable = false)
    private Date arrivalDate;

    @Column(name = "flight_duration",
            nullable = false)
    private Integer flightDurationInMinutes;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id",  nullable = false)
    private Airline airline;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id",  nullable = false)
    private Route route;

    @OneToMany(mappedBy = "flight",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Ticket> tickets = new ArrayList<>();
}
