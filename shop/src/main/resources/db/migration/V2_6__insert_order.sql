insert into Customer(firstName,lastName,username,`password`,email) values('Andrei','Maxim','protocala','protocala123','email@example.com');
insert into Address(country,city,county,streetAddress) values('Romania','Turda','Cluj','address3');
insert into `Order`(shippedFrom,idCustomer,shippedTo,createdAt) values(1,1,2,'2008-11-11');
insert into OrderDetail(idOrder,idProduct,quantity) values(1,1,4);