alter table `Order` add
  column shippedTo int
after
  idCustomer;
alter table `Order` add
  FOREIGN KEY (shippedTo) REFERENCES Address(id)