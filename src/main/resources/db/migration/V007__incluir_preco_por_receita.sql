-- Cria tabela de preço

create table preco_receita
(
    id_preco_receita   int auto_increment
        primary key,
    id_receita         int               not null,
    id_forma_venda     int               not null,
    preco              double            not null,
    preco_promocional  tinyint default 0 not null,
    nome_promocao      varchar(75)       null,
    dt_inicio_vigencia datetime          not null,
    dt_fim_vigencia    datetime          null,
    constraint preco_receita_FK_forma_venda
        foreign key (id_forma_venda) references forma_venda (id_forma_venda),
    constraint preco_receita_FK_receita
        foreign key (id_receita) references receita (id_receita)
)
    comment 'Armazena os preços de venda das receitas de acordo com a forma de venda';


-- Deleta coluna de preço da tabela RECEITA

alter table receita
    drop column preco_venda;
