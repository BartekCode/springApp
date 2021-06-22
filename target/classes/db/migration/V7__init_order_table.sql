drop  table if exists orders;
create  table orders(
                                    id int primary key auto_increment not null ,
                                    user_id int not null,
                                    product_id int
);

alter table orders add foreign key (product_id) references  products (id);
alter table orders add foreign key (user_id) references  users (id);

