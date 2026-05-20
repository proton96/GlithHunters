package com.glitchhunters.portalempresa.dto;

import lombok.Data;

// creación o actualización del producto
@Data
public class ProductDto {
    private String name;
    private String description;
    private int stock;
    private double price;
}
