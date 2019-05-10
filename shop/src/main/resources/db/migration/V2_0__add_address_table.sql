create table if not exists `Address`(
`id` int primary key auto_increment,
`country` varchar(50) not null,
`city` varchar(50) not null,
`county` varchar(50) not null,
`streetAddress` varchar(50))