package com.bbva.rentas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SolicitudRentaRequestDto {
    private String tipoIdentificacion;
    private String tipoDocumento;
    private String tipoPrimerApellido;
    private String tipoSegundoApellido;
    private String tipoPrimerNombre;
    private String tipoSegundoNombre;
    private String tipoGenero;
    private String tipoFechaNacimiento;
    private String tipoEstadoExpedicion;
    private String tipoEstadoActual;
    private String tipoNumeroPoliza;
    private String tipoFechaExpedicion;
    private String tipoFechaDerecho;
    private String tipoInteresTecnico;
    private String tipoOrigenPrestacion;
    private String tipoModalidadPrestacion;
    private String tipoFechaInicioRenDif;
    private String tipoAfpVen;
    private String tipoValorMesada;
    private String tipoNumeroMesadas;
    private String tipoValorReservaInVe;
    private String tipoValorReservaSob;
    private String tipoValorReservaAux;
    private String tipoValorOtrReserva;
    private String tipoGastosAdmon;
    private String tipoValorTotRes;
    private String docSoporteJudicial;
    private String fechaDocSopteJud;
    private String observacionesFalloJud;
    private String tipoTipoBeneficiario;
    private String tipoTipoDocumento;
    private String tipoDocumentoBeneficiario;
    private String tipoPrimerApellidoBeneficiario;
    private String tipoSegundoApellidoBeneficiario;
    private String tipoPrimerNombreBeneficiario;
    private String tipoSegundoNombreBeneficiario;
    private String tipoGeneroBeneficiario;
    private String tipoFechaNacimientoBenef;
    private String tipoIndEstudiaAct;
    private String tipoIndDependeEco;
    private String tipoEstadoExpedicionBeneficiario;
    private String tipoEstadoActualBeneficiario;
    private String temporBeneficiario;
    private String codigoTransaccion;


}
