package com.elitsoft.#app_name#.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class #Base#MobileDto {

    private Long productId;
    private int quantity;


    @Override
    public String toString() {
        return "#Base#Dto{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

}