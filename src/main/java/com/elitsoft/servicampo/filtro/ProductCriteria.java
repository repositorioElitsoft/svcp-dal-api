package com.elitsoft.servicampo.filtro;

import lombok.Data;

@Data
public class ProductCriteria {
    private String name;
    private String category;
    private double minPrice;
    private double maxPrice;
    // ... other product-specific filter fields
}