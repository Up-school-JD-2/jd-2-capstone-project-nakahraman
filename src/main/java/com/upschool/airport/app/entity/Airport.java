package com.upschool.airport.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "airport")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE airport SET active = false WHERE id=?")
@Where(clause = "active = true")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "airport_code",
            unique = true,
            nullable = false,
            length = 3)
    private String airportCode;
    @Column(name = "airport_name",
            unique = true,
            nullable = false,
            length = 50)
    private String airportName;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "fromAirport",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Route> routesDeparture = new ArrayList<>();

    @OneToMany(mappedBy = "toAirport",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Route> routesArrival = new ArrayList<>();


}
