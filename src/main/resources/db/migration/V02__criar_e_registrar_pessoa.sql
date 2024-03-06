CREATE TABLE pessoa (
    codigo BIGINT (20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (30) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logadouro VARCHAR(255),
    numero VARCHAR(20),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cep VARCHAR(20),
    cidade VARCHAR(255),
    estado VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Inserção de dados na tabela pessoa
INSERT INTO pessoa (nome, ativo, logadouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('João Silva', true, 'Rua A', '123', 'Apto 1', 'Centro', '12345-678', 'Cidade A', 'Estado A');

INSERT INTO pessoa (nome, ativo, logadouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Maria Oliveira', true, 'Rua B', '456', 'Casa 2', 'Bairro X', '54321-876', 'Cidade B', 'Estado B');

INSERT INTO pessoa (nome, ativo, logadouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Carlos Oliveira', true, 'Rua C', '789', 'Sala 3', 'Bairro Y', '98765-432', 'Cidade C', 'Estado C');

INSERT INTO pessoa (nome, ativo, logadouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Ana Souza', true, 'Avenida D', '101', 'Bloco 5', 'Centro', '54321-987', 'Cidade D', 'Estado D');

INSERT INTO pessoa (nome, ativo, logadouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Rodrigo Santos', false, 'Rua E', '222', 'Casa 7', 'Bairro Z', '76543-210', 'Cidade E', 'Estado E');

INSERT INTO pessoa (nome, ativo, logadouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Fernanda Lima', true, 'Avenida F', '333', 'Apartamento 8', 'Bairro W', '87654-321', 'Cidade F', 'Estado F');

INSERT INTO pessoa (nome, ativo, logadouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Ricardo Alves', true, 'Rua G', '444', 'Casa 10', 'Bairro V', '65432-109', 'Cidade G', 'Estado G');

INSERT INTO pessoa (nome, ativo, logadouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Juliana Pereira', false, 'Avenida H', '555', 'Sala 12', 'Centro', '10987-654', 'Cidade H', 'Estado H');

INSERT INTO pessoa (nome, ativo, logadouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Marcos Santos', true, 'Rua I', '666', 'Casa 15', 'Bairro U', '54321-234', 'Cidade I', 'Estado I');

INSERT INTO pessoa (nome, ativo, logadouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Amanda Oliveira', true, 'Avenida J', '777', 'Apartamento 18', 'Bairro T', '87654-321', 'Cidade J', 'Estado J');

