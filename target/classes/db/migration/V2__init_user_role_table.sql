drop table if exists roles;
create table roles(
                      id int primary key auto_increment not null,
                      role varchar (25) not null,
                      description varchar (100)
);


