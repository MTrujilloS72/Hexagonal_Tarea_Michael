package com.codigo.msregisterhexagonal.infraestructure.mapper;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.EmpresaDto;
import com.codigo.msregisterhexagonal.domain.aggregates.dto.PersonDto;
import com.codigo.msregisterhexagonal.infraestructure.entity.EmpresaEntity;
import com.codigo.msregisterhexagonal.infraestructure.entity.PersonEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmpresaMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public EmpresaDto mapToDto(EmpresaEntity empresaEntity){
        return MODEL_MAPPER.map(empresaEntity, EmpresaDto.class);
    }

    public EmpresaEntity mapToEntity(EmpresaDto empresaDto){
        return MODEL_MAPPER.map(empresaDto, EmpresaEntity.class);
    }
}
