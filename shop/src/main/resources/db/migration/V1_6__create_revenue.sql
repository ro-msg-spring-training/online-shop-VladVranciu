create table `Revenue`(
`id` int primary key auto_increment,
`idLocation` int,
constraint FK_RevenueLocation foreign key (idLocation) references Location(id),
`date` date not null,
`sum` numeric not null)