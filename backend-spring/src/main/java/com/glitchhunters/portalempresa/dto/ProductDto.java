package com.glitchhunters.portalempresa.dto;

import lombok.Data;
import java.math.BigDecimal;

// datos para crear o actualizar un producto
@Data
public class ProductDto {
    private String name;
    private String sku;
    private int stock;
    private int minStock;
    private BigDecimal price;
}
