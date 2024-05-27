CREATE TABLE cliente (
    id_cliente serial PRIMARY KEY,
    nome varchar(255),
    telefone varchar(255),
    cpf varchar(15),
    dt_nasc date,
    email varchar(255),
    bairro varchar(255),
    cep varchar(255),
    rua varchar(255),
    numero integer,
    complemento varchar(255),    
    cidade varchar(255),
    uf varchar(255)  
);

CREATE TABLE categoria(
    id_categoria serial PRIMARY KEY,
    nome varchar(255),
    descricao varchar(255)
);

CREATE TABLE pedido (
    id_pedido serial PRIMARY KEY,
    dt_entrega date,
    dt_envio date,
    dt_pedido date,
    status varchar(255) CHECK (status IN ('AGUARDANDO_PAGAMENTO', 'PAGO', 'EM_TRANSITO', 'ENTREGUE', 'CANCELADO')),
    vlr_total numeric(38,2),
    id_cliente int,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE produto (
    id_produto serial PRIMARY KEY,
    descricao varchar(255),
    dt_cadastro date,
    imagem varchar(255),
    qtd_estoque integer,
    vlr_unitario numeric(38,2),
    id_categoria int,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE item_pedido (
    id_item_pedido serial PRIMARY KEY,
    qnt integer,
    preco_venda numeric(38,2),
    percentual integer,
    vlr_bruto numeric(38,2),
    vlr_liquido numeric(38,2),
    id_pedido int,
    id_produto int,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);
