package com.itau.pix.resource.pixkey.mapper;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.dto.PixKeyDTO.PixKeyDTOBuilder;
import com.itau.pix.resource.pixkey.request.PixKeyCreateRequest;
import com.itau.pix.resource.pixkey.request.PixKeyGetRequest;
import com.itau.pix.resource.pixkey.request.PixKeyUpdateRequest;
import com.itau.pix.resource.pixkey.response.PixKeyResponse;
import com.itau.pix.resource.pixkey.response.PixKeyResponse.PixKeyResponseBuilder;
import com.itau.pix.resource.pixkey.response.PixKeyUpdateResponse;
import com.itau.pix.resource.pixkey.response.PixKeyUpdateResponse.PixKeyUpdateResponseBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-07T23:05:56-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class PixKeyDTOMapperImpl implements PixKeyDTOMapper {

    @Override
    public PixKeyDTO toDto(PixKeyCreateRequest pixKeyCreateRequest) {
        if ( pixKeyCreateRequest == null ) {
            return null;
        }

        PixKeyDTOBuilder pixKeyDTO = PixKeyDTO.builder();

        pixKeyDTO.key( pixKeyCreateRequest.getKey() );
        pixKeyDTO.type( pixKeyCreateRequest.getType() );
        pixKeyDTO.accountType( pixKeyCreateRequest.getAccountType() );
        pixKeyDTO.account( pixKeyCreateRequest.getAccount() );
        pixKeyDTO.agency( pixKeyCreateRequest.getAgency() );
        pixKeyDTO.customerName( pixKeyCreateRequest.getCustomerName() );
        pixKeyDTO.customerLastName( pixKeyCreateRequest.getCustomerLastName() );

        return pixKeyDTO.build();
    }

    @Override
    public PixKeyDTO toDto(PixKeyUpdateRequest pixKeyUpdateRequest) {
        if ( pixKeyUpdateRequest == null ) {
            return null;
        }

        PixKeyDTOBuilder pixKeyDTO = PixKeyDTO.builder();

        pixKeyDTO.id( pixKeyUpdateRequest.getId() );
        pixKeyDTO.accountType( pixKeyUpdateRequest.getAccountType() );
        pixKeyDTO.account( pixKeyUpdateRequest.getAccount() );
        pixKeyDTO.agency( pixKeyUpdateRequest.getAgency() );
        pixKeyDTO.customerName( pixKeyUpdateRequest.getCustomerName() );
        pixKeyDTO.customerLastName( pixKeyUpdateRequest.getCustomerLastName() );

        return pixKeyDTO.build();
    }

    @Override
    public PixKeyDTO toDto(PixKeyGetRequest pixKeyGetRequest) {
        if ( pixKeyGetRequest == null ) {
            return null;
        }

        PixKeyDTOBuilder pixKeyDTO = PixKeyDTO.builder();

        pixKeyDTO.accountType( pixKeyGetRequest.getAccountType() );
        pixKeyDTO.account( pixKeyGetRequest.getAccount() );
        pixKeyDTO.agency( pixKeyGetRequest.getAgency() );
        pixKeyDTO.customerName( pixKeyGetRequest.getCustomerName() );
        pixKeyDTO.creationDate( pixKeyGetRequest.getCreationDate() );
        if ( pixKeyGetRequest.getDisableDate() != null ) {
            pixKeyDTO.disableDate( LocalDateTime.parse( pixKeyGetRequest.getDisableDate() ) );
        }

        return pixKeyDTO.build();
    }

    @Override
    public PixKeyUpdateResponse toUpdateResponse(PixKeyDTO pixKeyDTO) {
        if ( pixKeyDTO == null ) {
            return null;
        }

        PixKeyUpdateResponseBuilder pixKeyUpdateResponse = PixKeyUpdateResponse.builder();

        pixKeyUpdateResponse.id( pixKeyDTO.getId() );
        pixKeyUpdateResponse.key( pixKeyDTO.getKey() );
        pixKeyUpdateResponse.type( pixKeyDTO.getType() );
        pixKeyUpdateResponse.accountType( pixKeyDTO.getAccountType() );
        pixKeyUpdateResponse.account( pixKeyDTO.getAccount() );
        pixKeyUpdateResponse.agency( pixKeyDTO.getAgency() );
        pixKeyUpdateResponse.customerName( pixKeyDTO.getCustomerName() );
        pixKeyUpdateResponse.customerLastName( pixKeyDTO.getCustomerLastName() );
        pixKeyUpdateResponse.creationDate( pixKeyDTO.getCreationDate() );

        return pixKeyUpdateResponse.build();
    }

    @Override
    public PixKeyResponse toResponse(PixKeyDTO pixKeyDTO) {
        if ( pixKeyDTO == null ) {
            return null;
        }

        PixKeyResponseBuilder pixKeyResponse = PixKeyResponse.builder();

        pixKeyResponse.id( pixKeyDTO.getId() );
        pixKeyResponse.key( pixKeyDTO.getKey() );
        pixKeyResponse.type( pixKeyDTO.getType() );
        pixKeyResponse.accountType( pixKeyDTO.getAccountType() );
        pixKeyResponse.account( pixKeyDTO.getAccount() );
        pixKeyResponse.agency( pixKeyDTO.getAgency() );
        pixKeyResponse.customerName( pixKeyDTO.getCustomerName() );
        pixKeyResponse.customerLastName( pixKeyDTO.getCustomerLastName() );
        pixKeyResponse.creationDate( pixKeyDTO.getCreationDate() );
        pixKeyResponse.disableDate( pixKeyDTO.getDisableDate() );

        return pixKeyResponse.build();
    }

    @Override
    public List<PixKeyResponse> toResponse(List<PixKeyDTO> pixKeyDTO) {
        if ( pixKeyDTO == null ) {
            return null;
        }

        List<PixKeyResponse> list = new ArrayList<PixKeyResponse>( pixKeyDTO.size() );
        for ( PixKeyDTO pixKeyDTO1 : pixKeyDTO ) {
            list.add( toResponse( pixKeyDTO1 ) );
        }

        return list;
    }
}
