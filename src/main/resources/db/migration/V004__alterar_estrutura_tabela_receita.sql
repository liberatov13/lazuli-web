-- Alterando nome da tabela RECEITA para RECEITA_ITEM
RENAME TABLE lazuli.receita TO lazuli.receita_item;


-- Alterando nome de coluna ID_RECEITA para ID_RECEITA_ITEM
ALTER TABLE lazuli.receita_item CHANGE id_receita id_receita_item int auto_increment NOT NULL;


-- Alterando nome de CONSTRAINTS e FKs
ALTER TABLE lazuli.receita_item
RENAME INDEX receita_FK_1 TO receita_item_FK_1;
ALTER TABLE lazuli.receita_item
RENAME INDEX receita_UN TO receita_item_UN;


-- Alterando nome de coluna
ALTER TABLE lazuli.receita_item CHANGE id_produto id_receita int NOT NULL COMMENT 'Chave estrangeira da tabela PRODUTO que indica o produto final gerado pela receita';


-- Alterando nome de FKs e apontando para PRODUTO e não mais PRODUTO
ALTER TABLE receita_item
DROP FOREIGN KEY receita_item_FK,
DROP FOREIGN KEY receita_item_FK_1;

ALTER TABLE receita_item
ADD CONSTRAINT `receita_item_FK` FOREIGN KEY (`id_receita`) REFERENCES `receita` (`id_receita`),
ADD CONSTRAINT `receita_item_FK_1` FOREIGN KEY (`id_ingrediente`) REFERENCES `produto` (`id_produto`);


-- Criando tabela de RECEITA
CREATE TABLE lazuli.receita (
	id_receita INT auto_increment NOT NULL,
	id_produto_final int NOT NULL,
	tempo_preparo TIME NULL COMMENT 'Tempo estimado de preparo da receita',
	CONSTRAINT receita_PK PRIMARY KEY (id_receita),
	CONSTRAINT receita_FK FOREIGN KEY (id_produto_final) REFERENCES lazuli.produto(id_produto)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;