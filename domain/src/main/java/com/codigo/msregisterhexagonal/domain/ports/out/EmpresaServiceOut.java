package com.codigo.msregisterhexagonal.domain.ports.out;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.EmpresaDto;

public interface EmpresaServiceOut {
    EmpresaDto createEmpresaOut(String ruc);
}
