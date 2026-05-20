package com.glitchhunters.portalempresa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

// Registro de cada entrada o salida de stock de un producto
@Entity
@Table(name = "stock_movements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // producto al que pertenece el movimiento
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private MovementType type;

    private int quantity;

    private LocalDate date;

    public enum MovementType {
        IN,  // entrada de stock
        OUT  // salida de stock
    }
}
