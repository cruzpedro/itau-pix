package com.itau.pix.service;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.entity.AccountType;
import com.itau.pix.entity.KeyType;
import com.itau.pix.entity.PixKey;
import com.itau.pix.exception.PixValidationException;
import com.itau.pix.mapper.PixKeyMapper;
import com.itau.pix.repository.PixKeyRepository;
import com.itau.pix.service.impl.PixKeyServiceImpl;
import com.itau.pix.strategy.customer.CustomerPFStrategy;
import com.itau.pix.strategy.customer.CustomerPJStrategy;
import com.itau.pix.strategy.customer.CustomerStrategyFactory;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.itau.pix.specification.PixKeySpecification.*;
import static com.itau.pix.specification.PixKeySpecification.customerName;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.data.jpa.domain.Specification.where;

@ExtendWith(SpringExtension.class)
public class PixKeyServiceTest {

    @Mock
    private PixKeyRepository pixKeyRepository;

    @Mock
    private CustomerStrategyFactory customerStrategyFactory;

    private PixKeyServiceImpl pixKeyService;

    private final KeyStrategyFactory keyStrategyFactory;
    private final PixKeyMapper pixKeyMapper;

    public PixKeyServiceTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        final Set<KeyStrategy> strategySet = Set.of(
                new CelularStrategy(),
                new AleatorioStrategy(),
                new EmailStrategy(),
                new CpfStrategy(validator),
                new CnpjStrategy(validator)
        );
        this.keyStrategyFactory = new KeyStrategyFactory(strategySet);
        this.pixKeyMapper = Mappers.getMapper(PixKeyMapper.class);
    }

    @BeforeEach
    void setup() {
        this.pixKeyService = new PixKeyServiceImpl(customerStrategyFactory, pixKeyRepository, pixKeyMapper);
    }

    @Test
    void shouldCreateCustomerPF() {
        Mockito.when(pixKeyRepository.save(any()))
                .thenReturn(PixKey.builder().id(UUID.randomUUID()).build());

        Mockito.when(customerStrategyFactory.findStrategy(any()))
                .thenReturn(new CustomerPFStrategy(pixKeyRepository, keyStrategyFactory, pixKeyMapper));

        final PixKeyDTO keyDTO = getPixKeyDTO("teste@mail.com", KeyType.EMAIL);
        final UUID uuid = pixKeyService.create(keyDTO);

        Assertions.assertNotNull(uuid);
    }

    @Test
    void shouldCreateCustomerPJ() {
        Mockito.when(pixKeyRepository.save(any()))
                .thenReturn(PixKey.builder().id(UUID.randomUUID()).build());

        Mockito.when(customerStrategyFactory.findStrategy(any()))
                .thenReturn(new CustomerPJStrategy(pixKeyRepository, keyStrategyFactory, pixKeyMapper));

        final PixKeyDTO keyDTO = getPixKeyDTO("17103338000171", KeyType.CNPJ);
        final UUID uuid = pixKeyService.create(keyDTO);

        Assertions.assertNotNull(uuid);
    }

    @Test
    void shouldFailWhenCreateCustomerWithInvalidKey() {
        Mockito.when(pixKeyRepository.save(any()))
                .thenReturn(PixKey.builder().id(UUID.randomUUID()).build());

        Mockito.when(customerStrategyFactory.findStrategy(any()))
                .thenReturn(new CustomerPFStrategy(pixKeyRepository, keyStrategyFactory, pixKeyMapper));

        Assertions.assertThrows(PixValidationException.class, () -> pixKeyService.create(getPixKeyDTO("testemail.com", KeyType.EMAIL)));
        Assertions.assertThrows(PixValidationException.class, () -> pixKeyService.create(getPixKeyDTO("17103338000170", KeyType.CNPJ)));
    }

    @Test
    void shouldFindById() {
        final UUID id = UUID.fromString("2598ddd0-5f09-11ed-9b6a-0242ac120002");
        Mockito.when(pixKeyRepository.findById(any()))
                .thenReturn(Optional.of(PixKey.builder().id(id).build()));

        final PixKeyDTO dto = pixKeyService.findById(id);

        Assertions.assertEquals(id, dto.getId());
    }

    @Test
    void shouldFindByIdNotFound() {
        final UUID id = UUID.fromString("2598ddd0-5f09-11ed-9b6a-0242ac120002");
        Mockito.when(pixKeyRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(PixValidationException.class, () -> pixKeyService.findById(id));
    }

    @Test
    void shouldFindByEmpty() {
        final PixKeyDTO pixKeyDTO = PixKeyDTO.builder()
                .customerName("User")
                .build();

        Mockito.when(pixKeyRepository.findAll(where(type(pixKeyDTO.getType())
                        .and(account(pixKeyDTO.getAgency(), pixKeyDTO.getAccount()))
                        .and(date(pixKeyDTO.getCreationDate(), pixKeyDTO.getDisableDate()))
                        .and(customerName(pixKeyDTO.getCustomerName())))))
                .thenReturn(Collections.emptyList());

        final List<PixKeyDTO> dto = pixKeyService.findBy(pixKeyDTO);

        Assertions.assertTrue(dto.isEmpty());
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
