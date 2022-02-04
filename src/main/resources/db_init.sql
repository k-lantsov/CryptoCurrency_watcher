create table currency
(
    id int primary key,
    symbol varchar(8) not null,
    cost decimal(16, 4)
);

insert into currency(id, symbol, cost)
VALUES (90, 'BTC', 0.0),
       (80, 'ETH', 0.0),
       (48543, 'SOL', 0.0);

create table client
(
    id int primary key auto_increment,
    username varchar(16),
    symbol varchar(8),
    registry_cost decimal(16, 4)
);