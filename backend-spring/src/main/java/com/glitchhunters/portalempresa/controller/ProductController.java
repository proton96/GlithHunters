package com.glitchhunters.portalempresa.controller;

import com.glitchhunters.portalempresa.dto.ProductDto;
import com.glitchhunters.portalempresa.entity.Product;
import com.glitchhunters.portalempresa.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// CRUD de productos — /api/products
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() { return productService.getAll(); }

    // productos con stock por debajo del umbral indicado (por defecto 5)
    @GetMapping("/low-stock")
    public List<Product> getLowStock(@RequestParam(defaultValue = "5") int threshold) {
        return productService.getLowStock(threshold);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) { return productService.getById(id); }

    @PostMapping
    public Product create(@RequestBody ProductDto dto) { return productService.create(dto); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody ProductDto dto) {
        return productService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
