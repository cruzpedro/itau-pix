package com.itau.pix.strategy.key;

import com.itau.pix.entity.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CelularStrategyTest {

    @Test
    void shouldIsValid() {
        final CelularStrategy celularStrategy = new CelularStrategy();
        final String key = "+5500912344321";

        Assertions.assertTrue(celularStrategy.isValid(key));
    }

    @Test
    void shouldIsInvalid() {
        final CelularStrategy celularStrategy = new CelularStrategy();
        final List<String> keys = List.of("5500912344321", "+500912344321", "+5500012344321", "55009123443210+");

        keys.forEach(key -> Assertions.assertFalse(celularStrategy.isValid(key)));
    }

    @Test
    void shouldIsValidType() {
        final CelularStrategy celularStrategy = new CelularStrategy();

        Assertions.assertEquals(celularStrategy.getType(), KeyType.CELULAR);
    }

}
