drop  table if exists confirmation_token;
create  table confirmation_token(
                             id int primary key auto_increment not null ,
                             user_id int not null,
                             token varchar(200) not null ,
                             created_at DATETIME not null ,
                             expired_at DATETIME not null ,
                             confirmed_at DATETIME,
                             FOREIGN KEY (user_id) REFERENCES users (id)
);
