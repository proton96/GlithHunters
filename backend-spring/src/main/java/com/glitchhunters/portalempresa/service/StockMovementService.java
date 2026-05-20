package com.glitchhunters.portalempresa.service;

import com.glitchhunters.portalempresa.dto.StockMovementDto;
import com.glitchhunters.portalempresa.entity.Product;
import com.glitchhunters.portalempresa.entity.StockMovement;
import com.glitchhunters.portalempresa.repository.ProductoRepository;
import com.glitchhunters.portalempresa.repository.StockMovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductoRepository productoRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository,
                                ProductoRepository productoRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productoRepository = productoRepository;
    }

    public List<StockMovement> getAll() { return stockMovementRepository.findAll(); }

    public List<StockMovement> getByProduct(Long productId) {
        return stockMovementRepository.findByProductId(productId);
    }

    @Transactional
    public StockMovement register(StockMovementDto dto) {
        Product product = productoRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + dto.getProductId()));

        // Update stock quantity
        if (dto.getType() == StockMovement.MovementType.IN) {
            product.setStock(product.getStock() + dto.getQuantity());
        } else {
            int newStock = product.getStock() - dto.getQuantity();
            if (newStock < 0) throw new RuntimeException("Stock insuficiente.");
            product.setStock(newStock);
        }
        productoRepository.save(product);

        // Record the movement
        StockMovement movement = StockMovement.builder()
                .product(product)
                .type(dto.getType())
                .quantity(dto.getQuantity())
                .timestamp(LocalDateTime.now())
                .notes(dto.getNotes())
                .build();

        return stockMovementRepository.save(movement);
    }
}
