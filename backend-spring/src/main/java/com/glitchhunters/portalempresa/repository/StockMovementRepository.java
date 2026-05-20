package com.glitchhunters.portalempresa.repository;

import com.glitchhunters.portalempresa.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    // historial de movimientos de un producto concreto
    // historial de movimientos de un producto concreto
    List<StockMovement> findByProductId(Long productId);
}
