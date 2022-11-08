package com.itau.pix.strategy.key;

import com.itau.pix.entity.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@ExtendWith(SpringExtension.class)
public class KeyStrategyFactoryTest {

    private final Set<KeyStrategy> strategySet;

    public KeyStrategyFactoryTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        this.strategySet = Set.of(
                new CelularStrategy(),
                new AleatorioStrategy(),
                new EmailStrategy(),
                new CpfStrategy(validator),
                new CnpjStrategy(validator)
        );
    }

    @Test
    void shouldFindStrategy() {
        final KeyStrategyFactory keyStrategyFactory = new KeyStrategyFactory(strategySet);

        Assertions.assertNotNull(keyStrategyFactory.findStrategy(KeyType.ALEATORIO));
        Assertions.assertNotNull(keyStrategyFactory.findStrategy(KeyType.CELULAR));
        Assertions.assertNotNull(keyStrategyFactory.findStrategy(KeyType.EMAIL));
        Assertions.assertNotNull(keyStrategyFactory.findStrategy(KeyType.CNPJ));
        Assertions.assertNotNull(keyStrategyFactory.findStrategy(KeyType.CPF));
    }

}
