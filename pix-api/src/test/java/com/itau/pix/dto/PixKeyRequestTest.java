package com.itau.pix.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixKeyRequestTest {

    private UUID id;
    private String key;
    private String type;

    @JsonProperty("account_type")
    private String accountType;

    private Integer account;
    private Integer agency;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_last_name")
    private String customerLastName;

}
