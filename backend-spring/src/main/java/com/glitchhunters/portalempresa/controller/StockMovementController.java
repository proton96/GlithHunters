package com.glitchhunters.portalempresa.controller;

import com.glitchhunters.portalempresa.dto.StockMovementDto;
import com.glitchhunters.portalempresa.entity.StockMovement;
import com.glitchhunters.portalempresa.service.StockMovementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// CRUD de movimientos de stock — /api/stock-movements
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    // devuelve todos los movimientos registrados
    @GetMapping
    public List<StockMovement> getAll() { return stockMovementService.getAll(); }

    // devuelve el historial de movimientos de un producto concreto
    @GetMapping("/product/{productId}")
    public List<StockMovement> getByProduct(@PathVariable Long productId) {
        return stockMovementService.getByProduct(productId);
    }

    // registra un movimiento y actualiza el stock del producto
    @PostMapping
    public StockMovement register(@RequestBody StockMovementDto dto) {
        return stockMovementService.register(dto);
    }
}
