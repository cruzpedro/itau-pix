package com.itau.pix.mapper;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.dto.PixKeyDTO.PixKeyDTOBuilder;
import com.itau.pix.entity.PixKey;
import com.itau.pix.entity.PixKey.PixKeyBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-07T23:38:02-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class PixKeyMapperImpl implements PixKeyMapper {

    @Override
    public PixKey toEntity(PixKeyDTO pixKeyDTO) {
        if ( pixKeyDTO == null ) {
            return null;
        }

        PixKeyBuilder pixKey = PixKey.builder();

        pixKey.id( pixKeyDTO.getId() );
        pixKey.type( pixKeyDTO.getType() );
        pixKey.accountType( pixKeyDTO.getAccountType() );
        pixKey.key( pixKeyDTO.getKey() );
        pixKey.agency( pixKeyDTO.getAgency() );
        pixKey.account( pixKeyDTO.getAccount() );
        pixKey.customerName( pixKeyDTO.getCustomerName() );
        pixKey.customerLastName( pixKeyDTO.getCustomerLastName() );
        pixKey.disableDate( pixKeyDTO.getDisableDate() );

        pixKey.active( true );
        pixKey.creationDate( java.time.LocalDateTime.now() );

        return pixKey.build();
    }

    @Override
    public PixKeyDTO toDto(PixKey pixKey) {
        if ( pixKey == null ) {
            return null;
        }

        PixKeyDTOBuilder pixKeyDTO = PixKeyDTO.builder();

        pixKeyDTO.id( pixKey.getId() );
        pixKeyDTO.key( pixKey.getKey() );
        pixKeyDTO.type( pixKey.getType() );
        pixKeyDTO.accountType( pixKey.getAccountType() );
        pixKeyDTO.account( pixKey.getAccount() );
        pixKeyDTO.agency( pixKey.getAgency() );
        pixKeyDTO.customerName( pixKey.getCustomerName() );
        pixKeyDTO.customerLastName( pixKey.getCustomerLastName() );
        pixKeyDTO.creationDate( pixKey.getCreationDate() );
        pixKeyDTO.disableDate( pixKey.getDisableDate() );

        return pixKeyDTO.build();
    }

    @Override
    public List<PixKeyDTO> toDto(List<PixKey> pixKey) {
        if ( pixKey == null ) {
            return null;
        }

        List<PixKeyDTO> list = new ArrayList<PixKeyDTO>( pixKey.size() );
        for ( PixKey pixKey1 : pixKey ) {
            list.add( toDto( pixKey1 ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(PixKeyDTO pixKeyDTO, PixKey pixKey) {
        if ( pixKeyDTO == null ) {
            return;
        }

        pixKey.setAccountType( pixKeyDTO.getAccountType() );
        pixKey.setAgency( pixKeyDTO.getAgency() );
        pixKey.setAccount( pixKeyDTO.getAccount() );
        pixKey.setCustomerName( pixKeyDTO.getCustomerName() );
        pixKey.setCustomerLastName( pixKeyDTO.getCustomerLastName() );
        pixKey.setDisableDate( pixKeyDTO.getDisableDate() );
    }
}
