package com.glitchhunters.portalempresa.entity;

import jakarta.persistence.*;
import lombok.*;

// Entidad que representa un departamento de la empresa
@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
}
