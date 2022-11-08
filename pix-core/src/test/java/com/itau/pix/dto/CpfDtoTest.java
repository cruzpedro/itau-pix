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
public class CpfDtoTest {

    private final Validator validator;

    public CpfDtoTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    void shouldValid() {
        final CpfDto cpf = CpfDto.builder()
                .cpf("09709218026")
                .build();

        final Set<ConstraintViolation<CpfDto>> violations = validator.validate(cpf);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void shouldInvalid() {
        final CpfDto cpf = CpfDto.builder()
                .cpf("09709218021")
                .build();

        final Set<ConstraintViolation<CpfDto>> violations = validator.validate(cpf);
        Assertions.assertFalse(violations.isEmpty());
    }
}
