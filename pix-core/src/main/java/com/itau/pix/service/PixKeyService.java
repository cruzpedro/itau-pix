package com.itau.pix.service;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.entity.KeyType;

import java.util.List;
import java.util.UUID;

public interface PixKeyService {

    UUID create(PixKeyDTO pixKeyDTO);
    PixKeyDTO update(PixKeyDTO pixKeyDTO);
    PixKeyDTO findById(UUID id);
    List<PixKeyDTO> findBy(PixKeyDTO pixKeyDTO);


}
