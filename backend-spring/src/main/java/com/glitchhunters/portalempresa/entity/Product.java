package com.glitchhunters.portalempresa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

// Entidad que representa un producto del inventario
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String sku;

    private int stock;

    // stock minimo para alertas de reposicion
    private int minStock;

    private BigDecimal price;
}
