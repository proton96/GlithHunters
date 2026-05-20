package com.glitchhunters.portalempresa.repository;

import com.glitchhunters.portalempresa.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findByProductId(Long productId);  // historial de un producto
}
