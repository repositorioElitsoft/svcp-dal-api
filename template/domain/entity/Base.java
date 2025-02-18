package com.elitsoft.#app_name#.domain.entity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Setter
@Getter
public class #Base# {

    private Long id;
    private Long productId;
    private int quantity;


    @Override
    public String toString() {
        return "#Base#{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}