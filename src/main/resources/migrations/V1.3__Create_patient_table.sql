create sequence if not exists patients_seq start with 1 increment by 50;
create table if not exists patients (birthdate timestamp(6), id serial not null, full_name varchar(255), primary key (id));