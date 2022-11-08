create schema if not exists audit;

create table revinfo
(
    rev      integer not null
        primary key,
    revtstmp bigint
);

alter table revinfo
    owner to postgres;

create table audit.tb_pix_key_aud
(
    id_pix_key             uuid    not null,
    rev                    integer not null
        constraint fkmy87td2nmh0gj3o2m38hj7hi7
            references public.revinfo,
    revtype                smallint,
    num_account            integer,
    ind_account_type       varchar(255),
    is_active              boolean,
    num_aggency            integer,
    created_at             timestamp,
    txt_customer_last_name varchar(255),
    txt_customer_name      varchar(255),
    disabled_at            timestamp,
    txt_key                varchar(255),
    ind_type               varchar(255),
    primary key (id_pix_key, rev)
);

alter table audit.tb_pix_key_aud
    owner to postgres;
