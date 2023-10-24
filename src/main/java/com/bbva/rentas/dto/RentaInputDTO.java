package com.bbva.rentas.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class RentaInputDTO {

    private String tipoNumeroPoliza;

    private String tipoFechaExpedicion;

    private String tipoFechaDerecho;

    private BigDecimal tipoInteresTecnico;

    //TODO: Cambiar por enum TipoOrigenPrestacion
    private String tipoOrigenPrestacion;

    //TODO: Cambiar por enum TipoModalidadPrestacion
    private String tipoModalidadPrestacion;

    private String tipoFechaInicioRenDif;

    private int tipoAfpVen;

    private BigDecimal tipoValorMesada;
    private int tipoNumeroMesadas;

    private BigDecimal tipoValorReservaInVe;

    private BigDecimal tipoValorReservaSob;

    private BigDecimal tipoValorReservaAux;

    private BigDecimal tipoValorOtrReserva;

    private BigDecimal tipoGastosAdmon;

    private BigDecimal tipoValorTotRes;

    private String docSoporteJudicial;

    private String fechaDocSopteJud;

    private String observacionesFalloJud;

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                tipoNumeroPoliza, tipoFechaExpedicion, tipoFechaDerecho, tipoInteresTecnico, tipoOrigenPrestacion,
                tipoModalidadPrestacion, tipoFechaInicioRenDif, tipoAfpVen, tipoValorMesada, tipoNumeroMesadas,
                tipoValorReservaInVe, tipoValorReservaSob, tipoValorReservaAux, tipoValorOtrReserva, tipoGastosAdmon,
                tipoValorTotRes, docSoporteJudicial, fechaDocSopteJud, observacionesFalloJud);
    }
}
