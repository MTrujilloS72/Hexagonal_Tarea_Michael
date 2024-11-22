package com.codigo.msregisterhexagonal.domain.ports.in;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.EmpresaDto;

public interface EmpresaServiceIn {
    EmpresaDto createEmpresaIn(String ruc);
}
