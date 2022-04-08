alter table venda drop constraint venda_FK_1;

ALTER TABLE lazuli.forma_venda MODIFY COLUMN id_forma_venda int auto_increment NOT NULL;

alter table lazuli.venda add constraint venda_FK_1 foreign key (id_forma_venda) references forma_venda (id_forma_venda);