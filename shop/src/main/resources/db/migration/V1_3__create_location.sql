create table `Location`(
`id` int primary key auto_increment,
`name` varchar(50) not null,
`country` varchar(50) not null,
`city` varchar(50) not null,
`county` varchar(50) not null,
`streetAddress` varchar(50))