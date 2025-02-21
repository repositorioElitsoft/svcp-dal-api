package com.elitsoft.servicampo.utils;

import lombok.Data;

@Data
public class PagingAndSorting {
    private int pageNumber = 0;
    private int pageSize = 10;
    private String sortField;
    private String sortDirection = "ASC";


/*
    public Sort.Direction getSortDirection() {
        if (sortDirection == null) {
            return Sort.Direction.ASC;
        }
        return Sort.Direction.fromString(this.sortDirection.toUpperCase());
    }
    */

}