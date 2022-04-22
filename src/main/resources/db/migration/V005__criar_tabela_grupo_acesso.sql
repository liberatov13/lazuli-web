-- Cria tabela de grupo de acessos ao sistema
create table grupo_acesso (
    id_grupo_acesso int auto_increment,
    nome            varchar(75)       not null,
    status          tinyint default 0 not null,
    constraint grupo_acesso_pk
        primary key (id_grupo_acesso)
)
comment 'Armazena os grupos de acesso ao sistema';

create unique index grupo_acesso_nome_uindex
on grupo_acesso (nome);


-- Cria tabela que vincula o usuario ao grupo
CREATE TABLE `usuario_grupo` (
    `id_usuario_grupo` int NOT NULL AUTO_INCREMENT,
    `id_usuario` int NOT NULL,
    `id_grupo` int NOT NULL,
    PRIMARY KEY (`id_usuario_grupo`),
    UNIQUE KEY `usuario_grupo_UN` (`id_usuario`,`id_grupo`),
    CONSTRAINT `usuario_grupo_FK` FOREIGN KEY (`id_grupo`) REFERENCES `grupo_acesso` (`id_grupo_acesso`),
    CONSTRAINT `usuario_grupo_FK_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Vincula os grupos que o usuário tem acesso';
