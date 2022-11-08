package com.itau.pix.strategy.customer;

import com.itau.pix.entity.KeyType;
import com.itau.pix.mapper.PixKeyMapper;
import com.itau.pix.repository.PixKeyRepository;
import com.itau.pix.strategy.key.AleatorioStrategy;
import com.itau.pix.strategy.key.CelularStrategy;
import com.itau.pix.strategy.key.CnpjStrategy;
import com.itau.pix.strategy.key.CpfStrategy;
import com.itau.pix.strategy.key.EmailStrategy;
import com.itau.pix.strategy.key.KeyStrategy;
import com.itau.pix.strategy.key.KeyStrategyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@ExtendWith(SpringExtension.class)
public class CustomerStrategyFactoryTest {

    @Mock
    private PixKeyRepository pixKeyRepository;

    private final KeyStrategyFactory keyStrategyFactory;
    private final PixKeyMapper pixKeyMapper;

    private Set<CustomerStrategy> customerStrategySet;

    public CustomerStrategyFactoryTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        final Set<KeyStrategy> keyStrategies = Set.of(
                new CelularStrategy(),
                new AleatorioStrategy(),
                new EmailStrategy(),
                new CpfStrategy(validator),
                new CnpjStrategy(validator)
        );
        this.keyStrategyFactory = new KeyStrategyFactory(keyStrategies);
        this.pixKeyMapper = Mappers.getMapper(PixKeyMapper.class);
    }

    @BeforeEach
    public void init() {
        customerStrategySet = Set.of(
                new CustomerPFStrategy(pixKeyRepository, keyStrategyFactory, pixKeyMapper),
                new CustomerPJStrategy(pixKeyRepository, keyStrategyFactory, pixKeyMapper)
        );
    }

    @Test
    void shouldFindStrategy() {
        final CustomerStrategyFactory customerStrategyFactory = new CustomerStrategyFactory(customerStrategySet);
        Assertions.assertInstanceOf(CustomerPFStrategy.class, customerStrategyFactory.findStrategy(KeyType.ALEATORIO));
        Assertions.assertInstanceOf(CustomerPFStrategy.class, customerStrategyFactory.findStrategy(KeyType.CELULAR));
        Assertions.assertInstanceOf(CustomerPFStrategy.class, customerStrategyFactory.findStrategy(KeyType.EMAIL));
        Assertions.assertInstanceOf(CustomerPFStrategy.class, customerStrategyFactory.findStrategy(KeyType.CPF));
        Assertions.assertInstanceOf(CustomerPJStrategy.class, customerStrategyFactory.findStrategy(KeyType.CNPJ));
    }

}
