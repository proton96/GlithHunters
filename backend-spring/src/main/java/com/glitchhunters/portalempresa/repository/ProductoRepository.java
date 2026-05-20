package com.glitchhunters.portalempresa.repository;

import com.glitchhunters.portalempresa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Product, Long> {
    // productos con stock por debajo del umbral (para alertas de bajo stock)
    List<Product> findByStockLessThan(int threshold);
}
