package com.upschool.airport.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE route SET active = false WHERE id=?")
@Where(clause = "active = true")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_airport_id",  nullable = false)
    private Airport fromAirport;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_airport_id", nullable = false)
    private Airport toAirport;

    @OneToMany(mappedBy = "route",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Flight> flights = new ArrayList<>();

}
