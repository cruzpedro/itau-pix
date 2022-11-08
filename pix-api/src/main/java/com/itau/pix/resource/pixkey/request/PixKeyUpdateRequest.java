package com.itau.pix.resource.pixkey.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.pix.entity.AccountType;
import com.itau.pix.entity.KeyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixKeyUpdateRequest {

    private UUID id;

    @JsonProperty("account_type")
    private AccountType accountType;

    private Integer account;
    private Integer agency;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_last_name")
    private String customerLastName;

}
