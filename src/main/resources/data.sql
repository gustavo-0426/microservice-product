insert into product(id, name, price) values(1, 'computer', 400);
insert into product(id, name, price) values(2, 'mouse', 10);

insert into customer(id, username, password, authorities) values(1, 'admin', '$2a$10$f8a6ikpS9gbijXTfl2aoEeFIcWrr.5D.XpynRVc3111EjiKk9KLWG', 'ROLE_admin');
insert into customer(id, username, password, authorities) values(2, 'user', '$2a$10$ajL/sS3ezRmalf22nQ9mUeEs9Roy7Xn3WU47R9z75uhLrFF0PXMIO', 'ROLE_user');