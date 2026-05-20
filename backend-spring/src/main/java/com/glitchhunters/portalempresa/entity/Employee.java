package com.glitchhunters.portalempresa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

// Entidad que representa a un empleado de la empresa
@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String email;
    private String phone;
    private BigDecimal salary;

    // id del departamento al que pertenece
    private Long departmentId;
}
