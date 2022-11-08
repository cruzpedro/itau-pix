package com.itau.pix.strategy.customer;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.entity.PixKey;
import com.itau.pix.mapper.PixKeyMapper;
import com.itau.pix.repository.PixKeyRepository;
import com.itau.pix.strategy.key.KeyStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerPFStrategy extends CustomerTemplate implements CustomerStrategy {
    private static final int MAX_RECORDS = 5;

    public CustomerPFStrategy(PixKeyRepository pixKeyRepository, KeyStrategyFactory keyStrategyFactory, PixKeyMapper pixKeyMapper) {
        super(pixKeyRepository, keyStrategyFactory, pixKeyMapper);
    }

    @Override
    public PixKey create(PixKeyDTO pixKeyDTO) {
        validate(pixKeyDTO, MAX_RECORDS);
        return save(pixKeyDTO);
    }

    @Override
    public CustomerType getType() {
        return CustomerType.PF;
    }
}
