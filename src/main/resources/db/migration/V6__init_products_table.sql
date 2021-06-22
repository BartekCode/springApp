drop table if exists products;
create table products(
                          id int primary key auto_increment,
                          category_id  int ,
                          name varchar(25) not null ,
                          description varchar (255) not null ,
                          price int not null,
                          FOREIGN KEY (category_id) REFERENCES categorys (id)
);


