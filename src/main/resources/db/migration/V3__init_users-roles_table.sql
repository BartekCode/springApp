drop  table if exists users_roles;
create  table users_roles(
                             roles_id int not null ,
                             user_id int not null ,
                             FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE,
                             FOREIGN KEY (roles_id) REFERENCES roles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
                             PRIMARY KEY (user_id, roles_id)
);

insert into roles (role, description) VALUES ("ROLE_USER", "default role for user");
insert into roles (role, description) VALUES ("ROLE_ADMIN", "admin for the boss");

