package com.elitsoft.servicampo.mapper;


import com.elitsoft.servicampo.filtro.UserCriteria;
import com.elitsoft.servicampo.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface UserMapper {

    Long agregar (User user);
    Long actualizar (User user);

    User findByUsername(String username);

    User findById(Long id); // Add this method

    //Beging Filters
    List<User> selectUsers(@Param("criteria") UserCriteria criteria,
                           @Param("sortField") String sortField,
                           @Param("sortDirection") String sortDirection,
                           @Param("limit") int limit,
                           @Param("offset") int offset);

    int countUsers(@Param("criteria") UserCriteria criteria);
}