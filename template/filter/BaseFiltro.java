package com.elitsoft.#app_name#.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class #Base#Filtro implements Serializable {

    private Long dmoId; // Replace with #base#Id
    private String dmoNom; // Replace with #base#Nom
    private String dmoCorreo; // Replace with #base#Correo
    private int dmoStatus; // Replace with #base#Status

}