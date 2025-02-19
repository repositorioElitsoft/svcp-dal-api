package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Product;
import com.elitsoft.servicampo.domain.criteria.ProductCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectProducts(@Param("criteria") ProductCriteria criteria,
                                 @Param("sortField") String sortField,
                                 @Param("sortDirection") String sortDirection,
                                 @Param("limit") int limit,
                                 @Param("offset") int offset);

    int countProducts(@Param("criteria") ProductCriteria criteria);
}