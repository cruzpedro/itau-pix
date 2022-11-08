package com.itau.pix.strategy.customer;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.entity.PixKey;
import com.itau.pix.exception.PixValidationException;
import com.itau.pix.mapper.PixKeyMapper;
import com.itau.pix.repository.PixKeyRepository;
import com.itau.pix.strategy.key.KeyStrategy;
import com.itau.pix.strategy.key.KeyStrategyFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class CustomerTemplate {

    protected final PixKeyRepository pixKeyRepository;
    protected final KeyStrategyFactory keyStrategyFactory;
    protected final PixKeyMapper pixKeyMapper;

    protected CustomerTemplate(PixKeyRepository pixKeyRepository, KeyStrategyFactory keyStrategyFactory, PixKeyMapper pixKeyMapper) {
        this.pixKeyRepository = pixKeyRepository;
        this.keyStrategyFactory = keyStrategyFactory;
        this.pixKeyMapper = pixKeyMapper;
    }

    protected void validate(PixKeyDTO pixKeyDTO, int maxRecords) {
        final List<PixKey> pixKeys = pixKeyRepository.findByAgencyAndAccount(pixKeyDTO.getAgency(), pixKeyDTO.getAccount());
        if (pixKeys.size() >= maxRecords) {
            throw new PixValidationException("Conta já possui o número máximo de chaves");
        }

        final KeyStrategy strategy = keyStrategyFactory.findStrategy(pixKeyDTO.getType());
        final boolean valid = strategy.isValid(pixKeyDTO.getKey());
        if (!valid) {
            throw new PixValidationException(String.format("Não é um valor válido para chave do tipo %s", pixKeyDTO.getType()));
        }

        if (pixKeyDTO.getAgency() > 9999) {
            throw new PixValidationException("Não é um valor válido para o campo agência");
        }

        if (pixKeyDTO.getAccount() > 99999999) {
            throw new PixValidationException("Não é um valor válido para o campo conta");
        }
    }

    protected PixKey save(PixKeyDTO pixKeyDTO) {
        try {
            final PixKey pixKey = pixKeyMapper.toEntity(pixKeyDTO);
            return pixKeyRepository.save(pixKey);
        } catch (Exception e) {
            log.error("C={}, M=create pixKeyDTO={}, message={}, e={}", getClass().getSimpleName(), pixKeyDTO, e.getMessage(), e);
            throw e;
        }
    }
}
