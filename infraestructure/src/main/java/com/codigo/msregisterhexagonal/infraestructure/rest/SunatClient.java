package com.codigo.msregisterhexagonal.infraestructure.rest;

import com.codigo.msregisterhexagonal.domain.aggregates.response.ResponseReniec;
import com.codigo.msregisterhexagonal.domain.aggregates.response.ResponseSunat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-sunat", url = "https://api.apis.net.pe/v2/sunat/ruc/")
public interface SunatClient {

    @GetMapping("/full")
    ResponseSunat getInfoSunat(@RequestParam("numero") String numero,
                               @RequestHeader("Authorization") String token);
}
