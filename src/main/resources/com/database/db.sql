create table if not exists DB (id identity primary key, name varchar(25) not null);
truncate table DB;
insert into DB (id, name) values (1, 'taco');
