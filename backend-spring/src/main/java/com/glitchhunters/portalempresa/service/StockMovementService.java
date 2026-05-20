package com.glitchhunters.portalempresa.service;

import com.glitchhunters.portalempresa.dto.StockMovementDto;
import com.glitchhunters.portalempresa.entity.Product;
import com.glitchhunters.portalempresa.entity.StockMovement;
import com.glitchhunters.portalempresa.repository.ProductoRepository;
import com.glitchhunters.portalempresa.repository.StockMovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    // historial de movimientos de un producto concreto
    public List<StockMovement> getByProduct(Long productId) {
        return stockMovementRepository.findByProductId(productId);
    }

    @Transactional
    public StockMovement register(StockMovementDto dto) {
        Product product = productoRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + dto.getProductId()));

        // actualizar el stock segun el tipo de movimiento
        if (dto.getType() == StockMovement.MovementType.IN) {
            product.setStock(product.getStock() + dto.getQuantity());
        } else {
            int nuevoStock = product.getStock() - dto.getQuantity();
            if (nuevoStock < 0) throw new RuntimeException("Stock insuficiente.");
            product.setStock(nuevoStock);
        }
        productoRepository.save(product);

        // guardar el registro del movimiento
        return stockMovementRepository.save(StockMovement.builder()
                .product(product)
                .type(dto.getType())
                .quantity(dto.getQuantity())
                .date(LocalDate.now())
                .build());
    }
}
