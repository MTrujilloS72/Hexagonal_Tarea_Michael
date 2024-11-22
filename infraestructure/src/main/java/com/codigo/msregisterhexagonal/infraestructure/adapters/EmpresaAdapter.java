package com.codigo.msregisterhexagonal.infraestructure.adapters;

import com.codigo.msregisterhexagonal.domain.aggregates.constants.Constants;
import com.codigo.msregisterhexagonal.domain.aggregates.dto.EmpresaDto;
import com.codigo.msregisterhexagonal.domain.aggregates.response.ResponseSunat;
import com.codigo.msregisterhexagonal.domain.ports.out.EmpresaServiceOut;
import com.codigo.msregisterhexagonal.infraestructure.dao.EmpresaRepository;
import com.codigo.msregisterhexagonal.infraestructure.entity.EmpresaEntity;
import com.codigo.msregisterhexagonal.infraestructure.mapper.EmpresaMapper;
import com.codigo.msregisterhexagonal.infraestructure.rest.SunatClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmpresaAdapter implements EmpresaServiceOut {
    private final EmpresaRepository empresaRepository;
    private final SunatClient sunatClient;
    private final EmpresaMapper empresaMapper;

    @Value("${token.api}")
    private String token;

    @Override
    public EmpresaDto createEmpresaOut(String ruc) {
        EmpresaEntity empresa = getEntity(ruc);
        EmpresaEntity dato = empresaRepository.save(empresa);
        EmpresaDto dto = empresaMapper.mapToDto(dato);
        log.info("dato del dto: " + dto.toString() );
        return dto;
    }

    private EmpresaEntity getEntity(String ruc){
        ResponseSunat responseSunat = execute(ruc);
        if(Objects.nonNull(responseSunat)){
            return EmpresaEntity.builder()
                    .razonSocial(responseSunat.getRazonSocial())
                    .tipoDocumento(responseSunat.getTipoDocumento())
                    .numeroDocumento(responseSunat.getNumeroDocumento())
                    .estado(responseSunat.getEstado())
                    .condicion(responseSunat.getCondicion())
                    .direccion(responseSunat.getDireccion())
                    .ubigeo(responseSunat.getUbigeo())
                    .viaTipo(responseSunat.getViaTipo())
                    .viaNombre(responseSunat.getViaNombre())
                    .zonaCodigo(responseSunat.getZonaCodigo())
                    .zonaTipo(responseSunat.getZonaTipo())
                    .numero(responseSunat.getNumero())
                    .interior(responseSunat.getInterior())
                    .lote(responseSunat.getLote())
                    .dpto(responseSunat.getDpto())
                    .manzana(responseSunat.getManzana())
                    .kilometro(responseSunat.getKilometro())
                    .distrito(responseSunat.getDistrito())
                    .provincia(responseSunat.getProvincia())
                    .departamento(responseSunat.getDepartamento())
                    .esAgenteRetencion(responseSunat.isEsAgenteRetencion())
                    .tipo(responseSunat.getTipo())
                    .actividadEconomica(responseSunat.getActividadEconomica())
                    .numeroTrabajadores(responseSunat.getNumeroTrabajadores())
                    .tipoFacturacion(responseSunat.getTipoFacturacion())
                    .tipoContabilidad(responseSunat.getTipoContabilidad())
                    .comercioExterior(responseSunat.getComercioExterior())
                    .date_crea(new Timestamp(System.currentTimeMillis()))
                    .usua_upda(Constants.USU_ADMIN)
                    .date_upda(new Timestamp(System.currentTimeMillis()))
                    .usua_dele(Constants.USU_ADMIN)
                    .date_dele(new Timestamp(System.currentTimeMillis()))
                    .build();
        }
        return null;
    }

    private ResponseSunat execute(String ruc){
        String head = "Bearer "+ token;
        return sunatClient.getInfoSunat(ruc, head);
    }
}
