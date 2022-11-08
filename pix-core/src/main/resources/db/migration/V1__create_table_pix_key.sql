create schema if not exists pix;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table pix.tb_pix_key
(
    id_pix_key             uuid        not null
        constraint tb_pix_key_pkey
            primary key,
    ind_type               varchar(9)  not null,
    txt_key                varchar(77) not null
        constraint tb_pix_key_key_unique
            unique,
    ind_account_type       varchar(10) not null,
    num_aggency            int         not null,
    num_account            int         not null,
    txt_customer_name      varchar(30) not null,
    txt_customer_last_name varchar(45),
    is_active              bool        not null,
    created_at             timestamp   not null,
    disabled_at            timestamp
);

alter table pix.tb_pix_key
    owner to postgres;
