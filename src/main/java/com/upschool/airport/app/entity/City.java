package com.upschool.airport.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE city SET active = false WHERE id=?")
@Where(clause = "active = true")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city_name",
            nullable = false,
            unique = true,
            length = 50)
    private String cityName;

    @Column(name = "active")
    @Builder.Default()
    private boolean active = Boolean.TRUE;

    @OneToMany(mappedBy = "city",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Airport> airports = new ArrayList<>();

}