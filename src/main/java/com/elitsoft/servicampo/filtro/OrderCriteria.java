package com.elitsoft.servicampo.filtro;

import lombok.Data;

@Data
public class OrderCriteria { // No need to implement any interface
    private String orderNumber;
    private String customerName;
    private String orderStatus;
    private Double minTotal;
    private Double maxTotal;
    // ... other order-specific criteria
}