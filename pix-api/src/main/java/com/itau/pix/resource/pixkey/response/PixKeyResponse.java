package com.itau.pix.resource.pixkey.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PixKeyResponse {

    private UUID id;
    private String key;
    private KeyType type;

    @JsonProperty("account_type")
    private AccountType accountType;

    private Integer account;
    private Integer agency;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_last_name")
    private String customerLastName;

    @JsonProperty("creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creationDate;

    @JsonProperty("disable_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime disableDate;

}
