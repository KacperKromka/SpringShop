delete from Design;
delete from Product;
delete from Product_Design;
delete from Product_Order;
delete from Product_Order_Products;

insert into Design (id, name, type) VALUES (1, 'czarne kropki', 'BLACKWHITE');
insert into Design (id, name, type) VALUES (2, 'czarne paski', 'BLACKWHITE');
insert into Design (id, name, type) VALUES (3, 'kolorowe  paski', 'COLOR');
insert into Design (id, name, type) VALUES (4, 'kolorowe kropki', 'COLOR');