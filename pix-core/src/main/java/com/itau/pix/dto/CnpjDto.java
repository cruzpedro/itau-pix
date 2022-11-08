package com.itau.pix.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@Builder
public class CnpjDto {

    @CNPJ
    private String cnpj;

}
