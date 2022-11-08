package com.itau.pix.strategy.key;

import com.itau.pix.entity.KeyType;
import org.springframework.stereotype.Component;

@Component
public class CelularStrategy implements KeyStrategy {
    @Override
    public boolean isValid(String key) {
        return key.matches("^\\+\\d{4}9\\d{8}") && key.length() == 14;
    }

    @Override
    public KeyType getType() {
        return KeyType.CELULAR;
    }
}
