drop table if exists account cascade
drop sequence if exists account_seq
create sequence account_seq start with 1 increment by 50
create table account (id bigint not null, email varchar(255), password varchar(255), username varchar(255), primary key (id))