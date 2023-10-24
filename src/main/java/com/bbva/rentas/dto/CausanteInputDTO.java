package com.bbva.rentas.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CausanteInputDTO {

    // TODO: change for enum TipoIdentificacion
    private String tipoIdentificacion;

    private String tipoDocumento;

    private String tipoPrimerApellido;

    private String tipoSegundoApellido;

    private String tipoPrimerNombre;

    private String tipoSegundoNombre;

    // TODO: change for enum
    private String tipoGenero;

    private String tipoFechaNacimiento;

    // TODO: change for enum TipoEstadoExpedicion
    private String tipoEstadoExpedicion;

    // TODO: change for enum TipoEstadoActual
    private String tipoEstadoActual;

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                tipoIdentificacion, tipoDocumento, tipoPrimerApellido, tipoSegundoApellido, tipoPrimerNombre, tipoSegundoNombre,
                tipoGenero, tipoFechaNacimiento, tipoEstadoExpedicion, tipoEstadoActual);
    }
}
