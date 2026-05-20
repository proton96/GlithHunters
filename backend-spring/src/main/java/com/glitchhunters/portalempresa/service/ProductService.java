package com.glitchhunters.portalempresa.service;

import com.glitchhunters.portalempresa.dto.ProductDto;
import com.glitchhunters.portalempresa.entity.Product;
import com.glitchhunters.portalempresa.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductoRepository productoRepository;

    public ProductService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Product> getAll() { return productoRepository.findAll(); }

    public Product getById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto con id " + id + " no encontrado."));
    }

    public Product create(ProductDto dto) {
        return productoRepository.save(Product.builder()
                .name(dto.getName())
                .sku(dto.getSku())
                .stock(dto.getStock())
                .minStock(dto.getMinStock())
                .price(dto.getPrice())
                .build());
    }

    public Product update(Long id, ProductDto dto) {
        Product p = getById(id);
        p.setName(dto.getName());
        p.setSku(dto.getSku());
        p.setStock(dto.getStock());
        p.setMinStock(dto.getMinStock());
        p.setPrice(dto.getPrice());
        return productoRepository.save(p);
    }

    public void delete(Long id) { productoRepository.deleteById(id); }

    // productos cuyo stock actual esta por debajo del umbral indicado
    public List<Product> getLowStock(int threshold) {
        return productoRepository.findByStockLessThan(threshold);
    }
}
