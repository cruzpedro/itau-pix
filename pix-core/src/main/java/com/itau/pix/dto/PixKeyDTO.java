package com.itau.pix.dto;

import com.itau.pix.entity.AccountType;
import com.itau.pix.entity.KeyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixKeyDTO {

    private UUID id;
    private String key;
    private KeyType type;
    private AccountType accountType;
    private Integer account;
    private Integer agency;
    private String customerName;
    private String customerLastName;
    private LocalDateTime creationDate;
    private LocalDateTime disableDate;

}
