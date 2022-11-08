package com.itau.pix.resource.pixkey.mapper;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.resource.pixkey.request.PixKeyCreateRequest;
import com.itau.pix.resource.pixkey.request.PixKeyGetRequest;
import com.itau.pix.resource.pixkey.request.PixKeyUpdateRequest;
import com.itau.pix.resource.pixkey.response.PixKeyResponse;
import com.itau.pix.resource.pixkey.response.PixKeyUpdateResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PixKeyDTOMapper {

    PixKeyDTO toDto(PixKeyCreateRequest pixKeyCreateRequest);
    PixKeyDTO toDto(PixKeyUpdateRequest pixKeyUpdateRequest);
    PixKeyDTO toDto(PixKeyGetRequest pixKeyGetRequest);

    PixKeyUpdateResponse toUpdateResponse(PixKeyDTO pixKeyDTO);
    PixKeyResponse toResponse(PixKeyDTO pixKeyDTO);
    List<PixKeyResponse> toResponse(List<PixKeyDTO> pixKeyDTO);

}
