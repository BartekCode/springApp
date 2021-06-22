drop table if exists categorys;
create table categorys(
                      id int primary key auto_increment,
                      name varchar(25) not null ,
                      description varchar (255) not null

);