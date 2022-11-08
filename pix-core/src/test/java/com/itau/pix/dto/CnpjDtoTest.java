package com.itau.pix.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class CnpjDtoTest {

    private final Validator validator;

    public CnpjDtoTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    void shouldValid() {
        final CnpjDto cnpj = CnpjDto.builder()
                .cnpj("17103338000171")
                .build();

        final Set<ConstraintViolation<CnpjDto>> violations = validator.validate(cnpj);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void shouldInvalid() {
        final CnpjDto cnpj = CnpjDto.builder()
                .cnpj("17103338000170")
                .build();

        final Set<ConstraintViolation<CnpjDto>> violations = validator.validate(cnpj);
        Assertions.assertFalse(violations.isEmpty());
    }
}
