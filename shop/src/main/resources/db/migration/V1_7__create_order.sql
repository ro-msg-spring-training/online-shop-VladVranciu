create table `Order`(
`id` int primary key auto_increment,
`shippedFrom` int,
`idCustomer` int,
constraint FK_OrderLocation foreign key (shippedFrom) references Location(id),
constraint FK_OrderCustomer foreign key (idCustomer) references Customer(id),
`createdAt` date not null,
`country` varchar(50) not null,
`city` varchar(50) not null,
`county` varchar(50) not null,
`streetAddress` varchar(50) not null)