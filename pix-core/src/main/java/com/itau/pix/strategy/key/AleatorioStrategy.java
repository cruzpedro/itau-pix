package com.itau.pix.strategy.key;

import com.itau.pix.entity.KeyType;
import org.springframework.stereotype.Component;

@Component
public class AleatorioStrategy implements KeyStrategy {
    @Override
    public boolean isValid(String key) {
        return key.matches("^[0-9a-zA-Z]{36}") && key.length() == 36;
    }

    @Override
    public KeyType getType() {
        return KeyType.ALEATORIO;
    }
}
