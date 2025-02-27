package com.elitsoft.#app_name#.domain.dto.core;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class #Base#Dto implements Serializable {

    private Long dmoId;
    private String dmoNom;
    private String dmoCorreo;
    private int dmoStatus;
}