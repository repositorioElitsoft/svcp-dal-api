package com.elitsoft.servicampo.mapper;


import com.elitsoft.servicampo.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface UserMapper {

    Long agregar (User user);
    Long actualizar (User user);

    User findByUsername(String username);

    User findById(Long id); // Add this method

}