alter table Product drop constraint FK_ProductSupplier;
alter table Product drop constraint FK_ProductProductCategory;

ALTER TABLE Product ADD FOREIGN KEY (idCategory) REFERENCES ProductCategory(id);
alter table Product add foreign key (idSupplier) references Supplier(id);