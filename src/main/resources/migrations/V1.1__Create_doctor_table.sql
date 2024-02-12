create sequence if not exists doctors_seq start with 1 increment by 50;
create table if not exists doctors (id serial not null, full_name varchar(255), profession varchar(255), primary key (id));