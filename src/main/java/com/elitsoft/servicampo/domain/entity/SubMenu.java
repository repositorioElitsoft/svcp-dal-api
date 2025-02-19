package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 *
 */
public class SubMenu {

    private Long id;
    private String nombre;
    private Menu menu;
    private List<Modulo> modulo;
}

