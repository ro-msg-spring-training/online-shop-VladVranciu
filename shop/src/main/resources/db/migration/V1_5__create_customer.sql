create table `Customer`(
`id` int primary key auto_increment,
`firstName` varchar(50) not null,
`lastName` varchar(50) not null,
`username` varchar(50) not null,
`password` varchar(250) not null,
`email` varchar(50) not null)