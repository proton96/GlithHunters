package com.glitchhunters.portalempresa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private MovementType type;

    private int quantity;

    private LocalDateTime timestamp;

    private String notes;

    public enum MovementType {
        IN,  // recibido / devuelto
        OUT  // retirado
    }
}
