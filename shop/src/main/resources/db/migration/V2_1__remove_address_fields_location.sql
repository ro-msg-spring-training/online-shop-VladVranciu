alter table
  Location drop column city,county,country,streetAddress;
alter table Location add
  column idAddress int unique
after
  name;
alter table Location add
  FOREIGN KEY (idAddress) REFERENCES Address(id)