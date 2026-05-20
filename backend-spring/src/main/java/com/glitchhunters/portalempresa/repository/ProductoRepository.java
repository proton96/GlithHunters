package com.glitchhunters.portalempresa.repository;

import com.glitchhunters.portalempresa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findByStockLessThan(int threshold);  // para alertar de bajo stock si hiciera falta
}
