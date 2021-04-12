create table if not exists Design (
    id bigint not null,
    name varchar(35) not null,
    type varchar(10) not null
);

create table if not exists Product (
    name varchar(45) not null,
    date timestamp not null,
    id identity
);

create table if not exists Product_Design (
    product bigint not null,
    design bigint not null
);

alter table Product_Design
    add foreign key (product) references Product(id);
alter table Product_Design
    add foreign key (design) references Design(id);

create table if not exists Product_Order (
    id bigint not null,
    date timestamp not null,
    deliveryName varchar(45) not null,
    deliveryStreet varchar(45) not null,
    deliveryCity varchar(45) not null,
    deliveryState varchar(45) not null,
    deliveryZip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null
);

create table if not exists Product_Order_Products (
    productOrder bigint not null,
    product bigint not null
);

alter table Product_Order_Products
    add foreign key (productOrder) references Product_Order(id);
alter table Product_Order_Products
    add foreign key (product) references Product(id);