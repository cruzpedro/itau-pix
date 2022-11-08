package com.itau.pix.strategy.customer;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.entity.AccountType;
import com.itau.pix.entity.KeyType;
import com.itau.pix.entity.PixKey;
import com.itau.pix.exception.PixException;
import com.itau.pix.exception.PixValidationException;
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
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class CustomerCnpjStrategyTest {

    @Mock
    private PixKeyRepository pixKeyRepository;

    private CustomerPJStrategy customerPJStrategy;
    private final KeyStrategyFactory keyStrategyFactory;
    private final PixKeyMapper pixKeyMapper;

    public CustomerCnpjStrategyTest() {
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
    void setup() {
        customerPJStrategy = new CustomerPJStrategy(pixKeyRepository, keyStrategyFactory, pixKeyMapper);
    }

    @Test
    void shouldCreate() {
        Mockito.when(pixKeyRepository.save(any()))
                .thenAnswer(i -> {
                    final PixKey argument = (PixKey) i.getArguments()[0];
                    argument.setId(UUID.randomUUID());

                    return argument;
                });

        final PixKey pixKey = customerPJStrategy.create(getPixKeyDTO("17103338000171", KeyType.CNPJ));

        Assertions.assertNotNull(pixKey.getId());
        Assertions.assertNotNull(pixKey.getCreationDate());
        Assertions.assertTrue(pixKey.getActive());
    }

    @Test
    void shouldFailWhenCreateWithInvalidKey() {
        Mockito.when(pixKeyRepository.save(any()))
                .thenAnswer(i -> {
                    final PixKey argument = (PixKey) i.getArguments()[0];
                    argument.setId(UUID.randomUUID());

                    return argument;
                });

        Assertions.assertThrows(PixValidationException.class, () -> customerPJStrategy.create(getPixKeyDTO("17103338000170", KeyType.CNPJ)));
    }

    @Test
    void shouldFailWhenCreate() {
        Mockito.when(pixKeyRepository.save(any()))
                .thenThrow(new PixException());

        Assertions.assertThrows(PixException.class, () -> customerPJStrategy.create(getPixKeyDTO("17103338000171", KeyType.CNPJ)));
    }

    @Test
    void shouldGetType() {
        Assertions.assertEquals(CustomerType.PJ, customerPJStrategy.getType());
    }

    private static PixKeyDTO getPixKeyDTO(String key, KeyType type) {
        return PixKeyDTO.builder()
                .key(key)
                .type(type)
                .accountType(AccountType.CORRENTE)
                .account(0)
                .agency(1)
                .customerName("User")
                .build();
    }
}
