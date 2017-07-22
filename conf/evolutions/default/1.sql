# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table gas_account (
  id                            bigint auto_increment not null,
  is_active                     tinyint(1) default 0 not null,
  username                      varchar(255),
  userPassword                      varchar(255),
  reference                     bigint not null,
  owner_id                      bigint not null,
  group_type                    varchar(7) not null,
  constraint ck_gas_account_group_type check ( group_type in ('OWNER','GAS','SUB_CLI','CLONE')),
  constraint pk_gas_account primary key (id)
);


# --- !Downs

drop table if exists gas_account;

