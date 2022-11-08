package com.itau.pix.strategy.key;

import com.itau.pix.dto.CpfDto;
import com.itau.pix.entity.KeyType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CpfStrategy implements KeyStrategy {

    private final Validator validator;

    @Override
    public boolean isValid(final String key) {
        final CpfDto cpf = CpfDto.builder()
                .cpf(key)
                .build();

        final Set<ConstraintViolation<CpfDto>> violations = validator.validate(cpf);

        return violations.isEmpty();
    }

    @Override
    public KeyType getType() {
        return KeyType.CPF;
    }
}
