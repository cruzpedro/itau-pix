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
public class CpfStrategyTest {

    private final Validator validator;

    public CpfStrategyTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    void shouldIsValid() {
        final CpfStrategy cpfStrategy = new CpfStrategy(validator);
        final String key = "09709218026";

        Assertions.assertTrue(cpfStrategy.isValid(key));
    }

    @Test
    void shouldIsInvalid() {
        final CpfStrategy cpfStrategy = new CpfStrategy(validator);
        final String key = "09709218027";

        Assertions.assertFalse(cpfStrategy.isValid(key));
    }

    @Test
    void shouldIsValidType() {
        final CpfStrategy cpfStrategy = new CpfStrategy(validator);

        Assertions.assertEquals(cpfStrategy.getType(), KeyType.CPF);
    }

}
