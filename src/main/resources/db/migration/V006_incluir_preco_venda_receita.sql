ALTER TABLE lazuli.receita ADD preco_venda DOUBLE NULL COMMENT 'Preço do produto final para venda';

ALTER TABLE lazuli.receita CHANGE preco_venda preco_venda double NULL COMMENT 'Preço do produto final para venda' AFTER id_produto_final;
