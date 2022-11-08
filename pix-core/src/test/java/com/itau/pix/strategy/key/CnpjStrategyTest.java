package com.itau.pix.strategy.key;

import com.itau.pix.entity.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@ExtendWith(MockitoExtension.class)
public class CnpjStrategyTest {

    private final Validator validator;

    public CnpjStrategyTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    void shouldIsValid() {
        final CnpjStrategy cnpjStrategy = new CnpjStrategy(validator);
        final String key = "17103338000171";

        Assertions.assertTrue(cnpjStrategy.isValid(key));
    }

    @Test
    void shouldIsInvalid() {
        final CnpjStrategy cnpjStrategy = new CnpjStrategy(validator);
        final String key = "17103338000170";

        Assertions.assertFalse(cnpjStrategy.isValid(key));
    }

    @Test
    void shouldIsValidType() {
        final CnpjStrategy cnpjStrategy = new CnpjStrategy(validator);

        Assertions.assertEquals(cnpjStrategy.getType(), KeyType.CNPJ);
    }

}
