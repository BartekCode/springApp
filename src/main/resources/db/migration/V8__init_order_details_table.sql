create table order_details (
                               id int not null,
                               amount double precision ,
                               price double precision ,
                               order_id int,
                               product_id int,
                               primary key (id)
);

alter table order_details
--     add constraint ORDER_DETAIL_ORD_FK
   add   foreign key (order_id)
            references orders (id);

alter table order_details
--     add constraint ORDER_DETAIL_PROD_FK
   add  foreign key (product_id)
            references products (id);