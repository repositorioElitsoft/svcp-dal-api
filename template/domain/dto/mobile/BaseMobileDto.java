package com.elitsoft.#app_name#.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class #Base#MobileDto implements Serializable {

    private Long dmoId;
    private String dmoNom;
    private String dmoCorreo;
    private int dmoStatus;

}