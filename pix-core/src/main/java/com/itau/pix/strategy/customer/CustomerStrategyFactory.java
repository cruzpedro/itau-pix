package com.itau.pix.strategy.customer;

import com.itau.pix.entity.KeyType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CustomerStrategyFactory {

    private final Map<CustomerType, CustomerStrategy> strategies;

    public CustomerStrategyFactory(Set<CustomerStrategy> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(strategy -> strategies.put(strategy.getType(), strategy));
    }

    public CustomerStrategy findStrategy(KeyType keyType) {
        return switch (keyType) {
            case CNPJ -> strategies.get(CustomerType.PJ);
            case CPF, EMAIL, CELULAR, ALEATORIO ->  strategies.get(CustomerType.PF);
        };
    }
}
