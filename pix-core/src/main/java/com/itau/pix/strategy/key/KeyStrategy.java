package com.itau.pix.strategy.key;

import com.itau.pix.entity.KeyType;

public interface KeyStrategy {

    boolean isValid(String key);

    KeyType getType();
}
