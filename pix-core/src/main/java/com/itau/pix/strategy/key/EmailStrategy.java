package com.itau.pix.strategy.key;

import com.itau.pix.entity.KeyType;
import org.springframework.stereotype.Component;

@Component
public class EmailStrategy implements KeyStrategy {
    @Override
    public boolean isValid(String key) {
        return key.matches("^(.+)@(.+)$") && key.length() <= 77;
    }

    @Override
    public KeyType getType() {
        return KeyType.EMAIL;
    }
}
