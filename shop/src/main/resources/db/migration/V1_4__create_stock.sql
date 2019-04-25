create table `Stock`(
`id` int primary key auto_increment,
`idProduct` int,
`idLocation` int,
constraint FK_StockProduct foreign key (idProduct) references Product(id),
constraint FK_StockLocation foreign key (idLocation) references Location(id),
`quantity` int not null)