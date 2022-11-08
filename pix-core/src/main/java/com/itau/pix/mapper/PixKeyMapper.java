package com.itau.pix.mapper;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.entity.PixKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PixKeyMapper {

    @Mapping(target = "active", constant = "true")
    @Mapping(target = "creationDate", expression = "java(java.time.LocalDateTime.now())")
    PixKey toEntity(PixKeyDTO pixKeyDTO);

    PixKeyDTO toDto(PixKey pixKey);
    List<PixKeyDTO> toDto(List<PixKey> pixKey);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "key", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    void updateFromDto(PixKeyDTO pixKeyDTO, @MappingTarget PixKey pixKey);

}
