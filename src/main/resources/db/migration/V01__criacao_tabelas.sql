CREATE TABLE cliente (
    id_cliente serial PRIMARY KEY,
    nome varchar(255),
    telefone varchar(255),
    cpf varchar(255),
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

CREATE TABLE pedido (
    id serial PRIMARY KEY,
    data_entrega date,
    data_envio date,
    data_pedido date,
    status varchar(255) CHECK (status IN ('AGUARDANDO_PAGAMENTO', 'PAGO', 'EM_TRANSITO', 'ENTREGUE', 'CANCELADO')),
    valor_total numeric(38,2),
    id_cliente int,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE categoria(
    id_categoria serial PRIMARY KEY,
    nome varchar(255),
    descricao varchar(255)
);

CREATE TABLE produto (
    id_produto serial PRIMARY KEY,
    descricao varchar(255),
    data_cadastro date,
    imagem varchar(255),
    quantidade_estoque integer,
    valor_unitario numeric(38,2),
    id_categoria int,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE item_pedido (
    id_item_pedido serial PRIMARY KEY,
    quantidade integer,
    preco_venda numeric(38,2),
    percentual integer,
    valor_bruto numeric(38,2),
    valor_liquido numeric(38,2),
    id_pedido int,
    id_produto int,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);
