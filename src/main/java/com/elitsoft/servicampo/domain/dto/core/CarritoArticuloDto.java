package com.elitsoft.servicampo.domain.dto.core;

//@Getter
//@Setter
//@Data
//@SuperBuilder
//@NoArgsConstructor
public class CarritoArticuloDto {



    // Getters and setters
    private Long productId;
    private int quantity;

    public CarritoArticuloDto(Long productId, int quantity) { // Constructor for inline query
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemDto{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}