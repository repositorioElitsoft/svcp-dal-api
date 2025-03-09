package com.elitsoft.servicampo.utils;

import com.elitsoft.servicampo.common.api.response.PagedResponse;
import org.springframework.data.domain.*;

import java.util.List;

public class PaginationUtils {

    public static <T> PagedResponse<T> createPagedResponse(
            List<T> content,
            int totalElements,  // Changed to long
            PagingAndSorting paginado) {

        Pageable pageable = PageRequest.of(paginado.getPageNumber(), paginado.getPageSize(), Sort.by(paginado.getSortDirection(), paginado.getSortField())); //Include sort information
        Page<T> page = new PageImpl<>(content, pageable, totalElements);

        PagedResponse<T> response = new PagedResponse<>();
        response.setContent(page.getContent());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLast(page.isLast());
        response.setFirst(page.isFirst());
        response.setPageNumber(page.getNumber());
        response.setNumberOfElements(page.getNumberOfElements());
        response.setPageSize(page.getSize());
        response.setEmpty(page.isEmpty());
        return response;
    }
}