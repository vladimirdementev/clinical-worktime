create sequence if not exists talons_seq start with 1 increment by 50;
create table if not exists talons (date date, start_time time(6), doctor_id serial, id serial not null, patient_id serial, primary key (id));
alter table if exists talons drop constraint if exists FKduev5ratsudw7s6dj8gyrgjkc;
alter table if exists talons drop constraint if exists FKrii9slsrqdxevsceyvjg61e6v;
alter table if exists talons add constraint FKduev5ratsudw7s6dj8gyrgjkc  foreign key (doctor_id) references doctors;
alter table if exists talons add constraint FKrii9slsrqdxevsceyvjg61e6v foreign key (patient_id) references patients;