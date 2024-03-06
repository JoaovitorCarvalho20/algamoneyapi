CREATE TABLE lancamento (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(100),
    tipo VARCHAR(20) NOT NULL,
    codigo_categoria BIGINT(20) NOT NULL,
    codigo_pessoa BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
    FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Conta de luz', '2024-03-10', '2024-03-10', 150.00, 'Pagamento da conta de luz', 'DESPESA', 1, 1);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Salário', '2024-03-05', '2024-03-05', 3000.00, 'Recebimento do salário', 'RECEITA', 2, 2);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Aluguel', '2024-03-15', NULL, 1200.00, 'Pagamento do aluguel', 'DESPESA', 3, 3);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Compra supermercado', '2024-03-08', '2024-03-08', 250.00, 'Compra mensal de supermercado', 'DESPESA', 4, 4);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Freelance', '2024-03-12', NULL, 500.00, 'Pagamento por trabalho freelance', 'RECEITA', 5, 5);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Internet', '2024-03-20', '2024-03-20', 80.00, 'Pagamento da conta de internet', 'DESPESA', 1, 1);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Frete', '2024-03-25', NULL, 120.00, 'Custo de frete para entrega de mercadorias', 'DESPESA', 4, 4);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Presente aniversário', '2024-03-15', '2024-03-15', 50.00, 'Compra de presente para aniversário', 'DESPESA', 5, 5);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Investimento', '2024-03-18', NULL, 1000.00, 'Investimento em ações', 'RECEITA', 6, 6);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Pagamento de empréstimo', '2024-03-10', '2024-03-10', 500.00, 'Reembolso de empréstimo a um amigo', 'RECEITA', 7, 7);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Manutenção do carro', '2024-03-28', NULL, 200.00, 'Serviços de manutenção do veículo', 'DESPESA', 3, 3);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Curso online', '2024-03-22', NULL, 150.00, 'Investimento em curso online', 'DESPESA', 2, 2);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Almoço de negócios', '2024-03-14', '2024-03-14', 80.00, 'Despesa relacionada a almoço de negócios', 'DESPESA', 8, 8);
