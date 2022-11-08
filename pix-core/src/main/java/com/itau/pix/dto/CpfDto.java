package com.itau.pix.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
public class CpfDto {

    @CPF
    private String cpf;

}
