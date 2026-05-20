package com.glitchhunters.portalempresa.dto;

import com.glitchhunters.portalempresa.entity.StockMovement;
import lombok.Data;

@Data
public class StockMovementDto {
    private Long productId;
    private StockMovement.MovementType type;  // IN o OUT
    private int quantity;
    private String notes;
}
