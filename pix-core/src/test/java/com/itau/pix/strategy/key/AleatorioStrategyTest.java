package com.itau.pix.strategy.key;

import com.itau.pix.entity.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AleatorioStrategyTest {

    @Test
    void shouldIsValid() {
        final AleatorioStrategy aleatorioStrategy = new AleatorioStrategy();
        final String key = "93u8mc3xy0ckm5dz7z5dkmkauwr4ja3kdfk5";

        Assertions.assertTrue(aleatorioStrategy.isValid(key));
    }

    @Test
    void shouldIsInvalid() {
        final AleatorioStrategy aleatorioStrategy = new AleatorioStrategy();
        final List<String> keys = List.of("93u8mc3xy0ckm5dz7z5dkmkauwr4ja3kdfk593u8mc3xy0ckm5dz7z5dkmkauwr4ja3kdfk5", "+500912344321", "+5500012344321", "55009123443210+");

        keys.forEach(key -> Assertions.assertFalse(aleatorioStrategy.isValid(key)));
    }

    @Test
    void shouldIsValidType() {
        final AleatorioStrategy aleatorioStrategy = new AleatorioStrategy();

        Assertions.assertEquals(aleatorioStrategy.getType(), KeyType.ALEATORIO);
    }

}
