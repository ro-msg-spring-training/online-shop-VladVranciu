create table `OrderDetail`(
`id` int primary key auto_increment,
`idOrder` int,
`idProduct` int,
constraint FK_OrderDetailOrder foreign key (idOrder) references `Order`(id),
constraint FK_OrderDetailProduct foreign key (idProduct) references Product(id),
`quantity` int not null)