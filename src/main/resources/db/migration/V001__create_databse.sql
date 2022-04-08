-------------------------------
-- Criação do Banco de Dados --
-------------------------------

-- Banco de dados
CREATE DATABASE `lazuli`;

USE lazuli;


-- Tipo_Produto
CREATE TABLE `tipo_produto` (
    `id_tipo_produto` int NOT NULL AUTO_INCREMENT COMMENT 'Código identificador',
    `nome` varchar(45) NOT NULL,
    PRIMARY KEY (`id_tipo_produto`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'Armazena os tipos de produtos permitidos.\nExemplo:  Venda, ingrediente, revenda...';

-- Marca
CREATE TABLE `marca` (
  `id_marca` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(75) NOT NULL,
  `status` tinyint NOT NULL,
  PRIMARY KEY (`id_marca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de armazenamento de marcas de produtos';

-- Unidade_Medida
CREATE TABLE `unidade_medida` (
  `id_unidade_medida` int NOT NULL AUTO_INCREMENT COMMENT 'Código identificador',
  `nome` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `simbolo` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` tinyint NOT NULL COMMENT 'Informa se o produto está ativo ou inativo',
  PRIMARY KEY (`id_unidade_medida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Armazena os tipos possíveis de pesagem';

-- Produto
CREATE TABLE `produto` (
  `id_produto` int NOT NULL AUTO_INCREMENT,
  `descricao_basica` varchar(75) NOT NULL COMMENT 'Descrição resumida do produto',
  `descricao_completa` varchar(100) DEFAULT NULL COMMENT 'Descrição detalhada sobre o produto',
  `id_marca` int DEFAULT NULL COMMENT 'Chave estrangeira da tabela MARCA',
  `cod_barras` bigint DEFAULT NULL COMMENT 'Código de barras do produto',
  `id_tipo_produto` int NOT NULL COMMENT 'Chave estrangeira da tabela TIPO_PRODUTO que indica a finalidade do produto',
  `id_unidade_medida` int NOT NULL COMMENT 'Chave estrangeira da tabela UNIDADE_MEDIDA que indica o tipo de unidade/pesagem do produto',
  `qtd_estoque` double DEFAULT NULL COMMENT 'Quantidade em estoque',
  `status` tinyint NOT NULL COMMENT 'Informa se o produto está ativo ou inativo',
  PRIMARY KEY (`id_produto`),
  KEY `produto_FK_2` (`id_tipo_produto`),
  KEY `produto_FK_3` (`id_marca`),
  KEY `produto_FK_4` (`id_unidade_medida`),
  CONSTRAINT `produto_FK_2` FOREIGN KEY (`id_tipo_produto`) REFERENCES `tipo_produto` (`id_tipo_produto`),
  CONSTRAINT `produto_FK_3` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`id_marca`),
  CONSTRAINT `produto_FK_4` FOREIGN KEY (`id_unidade_medida`) REFERENCES `unidade_medida` (`id_unidade_medida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de armazenamento de produtos';

-- Receita
CREATE TABLE `receita` (
  `id_receita` int NOT NULL AUTO_INCREMENT,
  `id_produto` int NOT NULL COMMENT 'Chave estrangeira da tabela PRODUTO que indica o produto final gerado pela receita',
  `id_ingrediente` int NOT NULL COMMENT 'Chave estrangeira da tabela PRODUTO que indica o ingrediente necerráio para o desenvolvimento da receita',
  `qtd_ingrediente` double DEFAULT NULL COMMENT 'Quantidade necessária do ingrediente para desenvolver a receita',
  PRIMARY KEY (`id_receita`),
  UNIQUE KEY `receita_UN` (`id_produto`,`id_ingrediente`),
  KEY `receita_FK_1` (`id_ingrediente`),
  CONSTRAINT `receita_FK` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`),
  CONSTRAINT `receita_FK_1` FOREIGN KEY (`id_ingrediente`) REFERENCES `produto` (`id_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Armazena os dados das receitas';

-- Pessoa
CREATE TABLE `pessoa` (
  `id_pessoa` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `dt_nascimento` date DEFAULT NULL,
  `documento` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Número de CPF ou CNPJ',
  `endereco` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id_pessoa`),
  UNIQUE KEY `documento_UN` (`documento`),
  UNIQUE KEY `email_UN` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de armazenamento de pessoas';

-- Usuario
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT COMMENT 'Código identificador do usuário',
  `id_pessoa` int NOT NULL COMMENT 'Chave estrangeira da tabela PESSOA que informa os dados pessoais',
  `nome_usuario` varchar(45) NOT NULL COMMENT 'Nome de usuário utilizado para acessar a aplicação',
  `senha` varchar(100) DEFAULT NULL,
  `status` tinyint NOT NULL COMMENT 'Informa se o usuário está ativo ou inativo para acessar o sistema',
  PRIMARY KEY (`id_usuario`),
  KEY `usuario_FK_2` (`id_pessoa`),
  CONSTRAINT `usuario_FK_2` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de armazenamento de dados sobre usuários do sistema';

-- Fornecedor
CREATE TABLE `fornecedor` (
  `id_fornecedor` int NOT NULL AUTO_INCREMENT,
  `id_pessoa` int NOT NULL COMMENT 'Chave estrangeira da tabela PESSOA',
  PRIMARY KEY (`id_fornecedor`),
  UNIQUE KEY `pessoa_UN` (`id_pessoa`),
  CONSTRAINT `fornecedor_FK` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de armazenamento de fornecedores/lojas';

CREATE TABLE `produto_fornecedor` (
  `id_produto_fornecedor` int NOT NULL AUTO_INCREMENT,
  `id_produto` int NOT NULL,
  `id_fornecedor` int NOT NULL,
  `custo_atacado` double DEFAULT NULL COMMENT 'Preço de compra em atacado',
  `custo_varejo` double DEFAULT NULL COMMENT 'Preço de compra em varejo',
  `qtd_atacado` double DEFAULT NULL COMMENT 'Quantidade necessário do produto para atingir o valor de atacado',
  PRIMARY KEY (`id_produto_fornecedor`),
  UNIQUE KEY `produto_fornecedor_UN` (`id_produto`,`id_fornecedor`),
  KEY `produto_fornecedor_FK_1` (`id_fornecedor`),
  CONSTRAINT `produto_fornecedor_FK` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`),
  CONSTRAINT `produto_fornecedor_FK_1` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Entidade relacional de produto e fornecedor';

-- Compra
CREATE TABLE `compra` (
  `id_compra` int NOT NULL AUTO_INCREMENT,
  `id_fornecedor` int NOT NULL COMMENT 'Chave estrangeira da tabela FORNECEDOR que indica onde foi feita a compra',
  `vl_total` double NOT NULL COMMENT 'Valor total gasto com a compra',
  `dt_compra` datetime NOT NULL COMMENT 'Data em que a compra foi realizada',
  PRIMARY KEY (`id_compra`),
  KEY `compra_FK_1` (`id_fornecedor`),
  CONSTRAINT `compra_FK_1` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de armazenamento de dados referentes a compras.';

-- Compra_Produto
CREATE TABLE `compra_produto` (
  `id_compra_produto` int NOT NULL AUTO_INCREMENT,
  `id_compra` int NOT NULL COMMENT 'Chave estrangeira da tabela COMPRA',
  `id_produto` int NOT NULL COMMENT 'Chave estrangeira da tabela PRODUTO que indica qual produto foi comprado',
  `qtd_comprada` double NOT NULL COMMENT 'Quantidade comprada do produto na compra',
  `vl_unidade` double DEFAULT NULL COMMENT 'Valor pago por unidade',
  PRIMARY KEY (`id_compra_produto`),
  KEY `compra_produto_FK` (`id_produto`),
  KEY `compra_produto_FK_1` (`id_compra`),
  CONSTRAINT `compra_produto_FK` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`),
  CONSTRAINT `compra_produto_FK_1` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id_compra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Entidade relacional de produtos comprados de acordo com a compra';

-- Cliente
CREATE TABLE `cliente` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `id_pessoa` int NOT NULL COMMENT 'Chave estrangeira da tabela PESSOA para obter os dados pessoais do cliente',
  `status` tinyint NOT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `cliente_FK` (`id_pessoa`),
  CONSTRAINT `cliente_FK` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de armazenamento de clientes';

-- Forma_Venda
CREATE TABLE `forma_venda` (
  `id_forma_venda` int NOT NULL,
  `nome` varchar(75) NOT NULL,
  PRIMARY KEY (`id_forma_venda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de armazenamento de tipos de vendas';

-- Venda
CREATE TABLE `venda` (
  `id_venda` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int DEFAULT NULL COMMENT 'Chave estrangeira da tabela CLIENTE que indica quem fez a compra',
  `vl_total` double NOT NULL COMMENT 'Valor total recebido pela compra',
  `id_forma_venda` int NOT NULL COMMENT 'Chave estrangeira da tabela FORMA_VENDA que indica por onde foi realizada a venda',
  `id_usuario_vendedor` int NOT NULL COMMENT 'Chave estrangeira da tabela USUARIO que indica quem realizou a venda',
  `dt_venda` datetime NOT NULL COMMENT 'Data em que foi feita a venda',
  PRIMARY KEY (`id_venda`),
  KEY `venda_FK` (`id_cliente`),
  KEY `venda_FK_1` (`id_forma_venda`),
  KEY `venda_FK_2` (`id_usuario_vendedor`),
  CONSTRAINT `venda_FK` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `venda_FK_1` FOREIGN KEY (`id_forma_venda`) REFERENCES `forma_venda` (`id_forma_venda`),
  CONSTRAINT `venda_FK_2` FOREIGN KEY (`id_usuario_vendedor`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabela de armazenamento de vendas';

-- Venda_Produto
CREATE TABLE `venda_produto` (
  `id_venda_produto` int NOT NULL AUTO_INCREMENT,
  `id_venda` int NOT NULL COMMENT 'Chave estrangeira da tabela VENDA',
  `id_produto` int NOT NULL COMMENT 'Chave estrangeira da tabela PRODUTO que indica o produto vendido',
  `qtd_produto` double NOT NULL COMMENT 'Quantidade vendida do produto',
  `vl_unitario` double DEFAULT NULL COMMENT 'Preço de venda por item',
  PRIMARY KEY (`id_venda_produto`),
  UNIQUE KEY `venda_produto_UN` (`id_venda`,`id_produto`),
  KEY `venda_produto_FK_1` (`id_produto`),
  CONSTRAINT `venda_produto_FK` FOREIGN KEY (`id_venda`) REFERENCES `venda` (`id_venda`),
  CONSTRAINT `venda_produto_FK_1` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Entidade relacional de produtos vendidos por venda';