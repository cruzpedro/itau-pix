package com.itau.pix.strategy.key;

import com.itau.pix.dto.CnpjDto;
import com.itau.pix.entity.KeyType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CnpjStrategy implements KeyStrategy {

    private final Validator validator;

    @Override
    public boolean isValid(final String key) {
        final CnpjDto cnpj = CnpjDto.builder()
                .cnpj(key)
                .build();

        final Set<ConstraintViolation<CnpjDto>> violations = validator.validate(cnpj);

        return violations.isEmpty();
    }

    @Override
    public KeyType getType() {
        return KeyType.CNPJ;
    }
}
