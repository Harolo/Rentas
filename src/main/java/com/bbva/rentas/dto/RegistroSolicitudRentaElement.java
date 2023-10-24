package com.bbva.rentas.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RegistroSolicitudRentaElement {

    private CausanteInputDTO tipoCausante;

    private RentaInputDTO tipoRenta;

    private List<BeneficiariosInputDTO> tipoBeneficiarios;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(String.format("%s;%s", tipoCausante, tipoRenta));
        for (BeneficiariosInputDTO benef : tipoBeneficiarios){
            str.append(";%s").append(benef);
        }
        return str.toString();
    }

    public void mapSolicitudRentaRequestDto(SolicitudRentaRequestDto solicitudRentaRequestDto) {
        CausanteInputDTO causa = new CausanteInputDTO();
        causa.setTipoIdentificacion(solicitudRentaRequestDto.getTipoIdentificacion());
        causa.setTipoDocumento(solicitudRentaRequestDto.getTipoDocumento());
        causa.setTipoPrimerApellido(solicitudRentaRequestDto.getTipoPrimerApellido());
        causa.setTipoSegundoApellido(solicitudRentaRequestDto.getTipoSegundoApellido());
        causa.setTipoPrimerNombre(solicitudRentaRequestDto.getTipoPrimerNombre());
        causa.setTipoSegundoNombre(solicitudRentaRequestDto.getTipoSegundoNombre());
        causa.setTipoFechaNacimiento(solicitudRentaRequestDto.getTipoFechaNacimiento());
        causa.setTipoGenero(solicitudRentaRequestDto.getTipoGenero());
        causa.setTipoEstadoExpedicion(solicitudRentaRequestDto.getTipoEstadoExpedicion());
        causa.setTipoEstadoActual(solicitudRentaRequestDto.getTipoEstadoActual());

        RentaInputDTO renta = new RentaInputDTO();
        renta.setTipoNumeroPoliza(solicitudRentaRequestDto.getTipoNumeroPoliza());
        renta.setTipoFechaExpedicion(solicitudRentaRequestDto.getTipoFechaExpedicion());
        renta.setTipoFechaDerecho(solicitudRentaRequestDto.getTipoFechaDerecho());
        renta.setTipoInteresTecnico(new BigDecimal(solicitudRentaRequestDto.getTipoInteresTecnico()));
        renta.setTipoOrigenPrestacion(solicitudRentaRequestDto.getTipoOrigenPrestacion());
        renta.setTipoModalidadPrestacion(solicitudRentaRequestDto.getTipoModalidadPrestacion());
        renta.setTipoFechaInicioRenDif(solicitudRentaRequestDto.getTipoFechaInicioRenDif());
        renta.setTipoAfpVen(Integer.parseInt(solicitudRentaRequestDto.getTipoAfpVen()));
        renta.setTipoValorMesada(new BigDecimal(solicitudRentaRequestDto.getTipoValorMesada()));
        renta.setTipoNumeroMesadas(Integer.parseInt(solicitudRentaRequestDto.getTipoNumeroMesadas()));
        renta.setTipoValorReservaInVe(new BigDecimal(solicitudRentaRequestDto.getTipoValorReservaInVe()));
        renta.setTipoValorReservaSob(new BigDecimal(solicitudRentaRequestDto.getTipoValorReservaSob()));
        renta.setTipoValorReservaAux(new BigDecimal(solicitudRentaRequestDto.getTipoValorReservaAux()));
        renta.setTipoValorOtrReserva(new BigDecimal(solicitudRentaRequestDto.getTipoValorOtrReserva()));
        renta.setTipoGastosAdmon(new BigDecimal(solicitudRentaRequestDto.getTipoGastosAdmon()));
        renta.setTipoValorTotRes(new BigDecimal(solicitudRentaRequestDto.getTipoValorTotRes()));
        renta.setDocSoporteJudicial(solicitudRentaRequestDto.getDocSoporteJudicial());
        renta.setFechaDocSopteJud(solicitudRentaRequestDto.getFechaDocSopteJud());
        renta.setObservacionesFalloJud(solicitudRentaRequestDto.getObservacionesFalloJud());




        List<BeneficiariosInputDTO> beneficiariosList = new ArrayList<>();
        BeneficiariosInputDTO beneficiario = new BeneficiariosInputDTO();
        beneficiario.setTipoTipoBeneficiario(solicitudRentaRequestDto.getTipoTipoBeneficiario());
        beneficiario.setTipoTipoDocumento(solicitudRentaRequestDto.getTipoTipoDocumento());
        beneficiario.setTipoDocumento(solicitudRentaRequestDto.getTipoDocumentoBeneficiario());
        beneficiario.setTipoPrimerApellido(solicitudRentaRequestDto.getTipoPrimerApellidoBeneficiario());
        beneficiario.setTipoSegundoApellido(solicitudRentaRequestDto.getTipoSegundoApellidoBeneficiario());
        beneficiario.setTipoPrimerNombre(solicitudRentaRequestDto.getTipoPrimerNombreBeneficiario());
        beneficiario.setTipoSegundoNombre(solicitudRentaRequestDto.getTipoSegundoNombreBeneficiario());
        beneficiario.setTipoGenero(solicitudRentaRequestDto.getTipoGeneroBeneficiario());
        beneficiario.setTipoFechaNacimiento(solicitudRentaRequestDto.getTipoFechaNacimientoBenef());
        beneficiario.setTipoIndEstudiaAct(solicitudRentaRequestDto.getTipoIndEstudiaAct());
        beneficiario.setTipoIndDependeEco(solicitudRentaRequestDto.getTipoIndDependeEco());
        beneficiario.setTipoEstadoExpedicion(solicitudRentaRequestDto.getTipoEstadoExpedicionBeneficiario());
        beneficiario.setTipoEstadoActual(solicitudRentaRequestDto.getTipoEstadoActualBeneficiario());
        beneficiario.setTemporBeneficiario(solicitudRentaRequestDto.getTemporBeneficiario());


        beneficiariosList.add(beneficiario);

        this.setTipoCausante(causa);
        this.setTipoRenta(renta);
        this.setTipoBeneficiarios(beneficiariosList);
    }

}
