package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZonaMobileDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 9130687054815060988L;

    private Long id;
    private String descripcionZona;
}