package com.itau.pix.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@Audited
@AuditTable(value = "tb_pix_key_aud", schema = "audit")
@Table(name = "tb_pix_key", schema = "pix")
@AllArgsConstructor
@NoArgsConstructor
public class PixKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pix_key", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(name = "ind_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private KeyType type;

    @Column(name = "ind_account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "txt_key", nullable = false)
    private String key;

    @Column(name = "num_aggency", nullable = false)
    private Integer agency;

    @Column(name = "num_account", nullable = false)
    private Integer account;

    @Column(name = "txt_customer_name", nullable = false)
    private String customerName;

    @Column(name = "txt_customer_last_name")
    private String customerLastName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "disabled_at")
    private LocalDateTime disableDate;

    @Column(name = "is_active")
    private Boolean active;

}
