package com.itau.pix.strategy.key;

import com.itau.pix.entity.KeyType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class KeyStrategyFactory {

    private final Map<KeyType, KeyStrategy> strategies;

    public KeyStrategyFactory(Set<KeyStrategy> strategySet) {
        this.strategies = new HashMap<>();
        strategySet.forEach(strategy -> strategies.put(strategy.getType(), strategy));
    }

    public KeyStrategy findStrategy(KeyType keyType) {
        return strategies.get(keyType);
    }
}
