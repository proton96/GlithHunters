package com.glitchhunters.portalempresa.dto;

import com.glitchhunters.portalempresa.entity.StockMovement;
import lombok.Data;

// datos para registrar un movimiento de stock
@Data
public class StockMovementDto {
    private Long productId;
    private StockMovement.MovementType type;  // IN o OUT
    private int quantity;
}
