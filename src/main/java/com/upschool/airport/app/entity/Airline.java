package com.upschool.airport.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airline")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE airline SET active = false WHERE id=?")
@Where(clause = "active = true")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "airline_code",
            unique = true,
            nullable = false,
            length = 3)
    private String airlineCode;

    @Column(name = "airline_name",
            nullable = false,
            length = 50)
    private String airlineName;

    @Column(name = "active")
    @Builder.Default()
    private boolean active = Boolean.TRUE;

    @OneToMany(mappedBy = "airline",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Flight> flights = new ArrayList<>();
}
