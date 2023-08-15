package com.upschool.airport.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE customer SET active = false WHERE id=?")
@Where(clause = "active = true")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "identity_number",
            unique = true,
            nullable = false,
            length = 11)
    private String identityNumber;
    @Column(name = "name",
            nullable = false,
            length = 50)
    private String name;
    @Column(name = "surname",
            nullable = false,
            length = 50)
    private String surname;
    @Column(name = "phone",
            nullable = false,
            unique = true,
            length = 15)
    private String phone;
    @Column(name = "email",
            nullable = false,
            unique = true,
            length = 50)
    private String email;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Ticket> tickets = new ArrayList<>();

}
