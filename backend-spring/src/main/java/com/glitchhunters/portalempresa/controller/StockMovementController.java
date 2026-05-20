package com.glitchhunters.portalempresa.controller;

import com.glitchhunters.portalempresa.dto.StockMovementDto;
import com.glitchhunters.portalempresa.entity.StockMovement;
import com.glitchhunters.portalempresa.service.StockMovementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @GetMapping
    public List<StockMovement> getAll() { return stockMovementService.getAll(); }

    /** GET /api/stock-movements/product/{productId} → movement history for one product */
    @GetMapping("/product/{productId}")
    public List<StockMovement> getByProduct(@PathVariable Long productId) {
        return stockMovementService.getByProduct(productId);
    }

    /** POST /api/stock-movements → registers a movement and updates the product's stock */
    @PostMapping
    public StockMovement register(@RequestBody StockMovementDto dto) {
        return stockMovementService.register(dto);
    }
}
