package com.itau.pix.strategy.customer;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.entity.PixKey;

public interface CustomerStrategy {

    PixKey create (PixKeyDTO pixKeyDTO);

    CustomerType getType();

}
