package com.elitsoft.servicampo.mapper;


import com.elitsoft.servicampo.domain.entity.CarritoArticulo;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 *
 */
@Mapper
public interface CarritoArticuloMapper {


    /**
     * @param carritoArticulo
     * @return
     */
    Long agregar(CarritoArticulo carritoArticulo);


    /**
     * @param carritoArticulo
     * @return
     */
    int actualizar(CarritoArticulo carritoArticulo);


    /**
     * @param id
     * @return
     */
    int eliminar(Long id);

    /**
     * @param id
     * @return
     */
    CarritoArticulo encontrarPorClave(Long id);

    /**
     * @return
     */
    List<CarritoArticulo> obtenerTodos();

}