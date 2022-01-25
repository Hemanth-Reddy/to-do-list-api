
--drop table if exists users;
create table if not exists users
(
   user_id varchar primary key,
   name varchar (100) not null,
   email varchar (100) not null unique,
   user_password varchar (100),
   age int,
   image bytea,
   created_at timestamp,
   updated_at timestamp
);
--drop table if exists tasks;
create table if not exists tasks
(
   task_id varchar primary key,
   owner_id varchar,
   description text not null,
   completed boolean,
   created_at timestamp,
   updated_at timestamp,
   CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES users (user_id)
);