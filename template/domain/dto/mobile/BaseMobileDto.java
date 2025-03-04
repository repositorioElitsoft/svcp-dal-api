package com.elitsoft.#app_name#.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class #Base#MobileDTO implements Serializable {

    private Long dmoId;
    private String dmoNom;
    private String dmoCorreo;
    private int dmoStatus;

}