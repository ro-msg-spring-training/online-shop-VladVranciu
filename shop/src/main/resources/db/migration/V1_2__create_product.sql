create table `Product`(
`id` int primary key auto_increment,
`name` varchar(50) not null,
`description` varchar(100) not null,
`price` numeric not null,
`weight` double not null,
`idCategory` int,
CONSTRAINT FK_ProductProductCategory FOREIGN KEY (idCategory) REFERENCES Product(id),
`idSupplier` int,
constraint FK_ProductSupplier foreign key (idSupplier) references Product(id),
`imageUrl` varchar(50))