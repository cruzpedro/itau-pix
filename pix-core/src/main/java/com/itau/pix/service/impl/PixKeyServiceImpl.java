package com.itau.pix.service.impl;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.entity.PixKey;
import com.itau.pix.exception.PixValidationException;
import com.itau.pix.mapper.PixKeyMapper;
import com.itau.pix.repository.PixKeyRepository;
import com.itau.pix.service.PixKeyService;
import com.itau.pix.strategy.customer.CustomerStrategy;
import com.itau.pix.strategy.customer.CustomerStrategyFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.itau.pix.specification.PixKeySpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@Service
@AllArgsConstructor
public class PixKeyServiceImpl implements PixKeyService {

    private final CustomerStrategyFactory customerStrategyFactory;
    private final PixKeyRepository pixKeyRepository;
    private final PixKeyMapper pixKeyMapper;

    @Override
    public UUID create(PixKeyDTO pixKeyDTO) {
        log.info("C={}, M=create, pixKeyDTO={}", getClass().getSimpleName(), pixKeyDTO);

        try {
            final CustomerStrategy strategy = customerStrategyFactory.findStrategy(pixKeyDTO.getType());
            final PixKey pixKey = strategy.create(pixKeyDTO);

            return pixKey.getId();
        } catch (Exception e) {
            log.error("C={}, M=create pixKeyDTO={}, message={}, e={}", getClass().getSimpleName(), pixKeyDTO, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public PixKeyDTO update(PixKeyDTO pixKeyDTO) {
        log.info("C={}, M=update, pixKeyDTO={}", getClass().getSimpleName(), pixKeyDTO);
        final PixKey pixKey = pixKeyRepository.findById(pixKeyDTO.getId())
                .orElseThrow(() -> new PixValidationException("Chave Pix não encontrada"));

        if (!pixKey.getActive()) {
            throw new PixValidationException("Não é possível atualizar uma Chave Pix inativada");
        }

        if (pixKeyDTO.getAgency() > 9999) {
            throw new PixValidationException("Não é um valor válido para o campo agência");
        }

        if (pixKeyDTO.getAccount() > 99999999) {
            throw new PixValidationException("Não é um valor válido para o campo conta");
        }

        try {
            pixKeyMapper.updateFromDto(pixKeyDTO, pixKey);
            return pixKeyMapper.toDto(pixKeyRepository.save(pixKey));
        } catch (Exception e) {
            log.error("C={}, M=update pixKeyDTO={}, message={}, e={}", getClass().getSimpleName(), pixKeyDTO, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public PixKeyDTO findById(UUID id) {
        log.info("C={}, M=findById, id={}", getClass().getSimpleName(), id);

        try {
            final PixKey pixKey = pixKeyRepository.findById(id).orElseThrow(() -> new PixValidationException("Chave Pix não encontrada"));
            return pixKeyMapper.toDto(pixKey);
        } catch (Exception e) {
            log.error("C={}, M=findById id={}, message={}, e={}", getClass().getSimpleName(), id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<PixKeyDTO> findBy(PixKeyDTO pixKeyDTO) {
        log.info("C={}, M=findBy, pixKeyDTO={}", getClass().getSimpleName(), pixKeyDTO);

        try {
            final List<PixKey> pixKeyList = pixKeyRepository.findAll(where(type(pixKeyDTO.getType())
                    .and(account(pixKeyDTO.getAgency(), pixKeyDTO.getAccount()))
                    .and(date(pixKeyDTO.getCreationDate(), pixKeyDTO.getDisableDate()))
                    .and(customerName(pixKeyDTO.getCustomerName()))));

            return pixKeyMapper.toDto(pixKeyList);
        } catch (Exception e) {
            log.error("C={}, M=findById pixKeyDTO={}, message={}, e={}", getClass().getSimpleName(), pixKeyDTO, e.getMessage(), e);
            throw e;
        }
    }
}
