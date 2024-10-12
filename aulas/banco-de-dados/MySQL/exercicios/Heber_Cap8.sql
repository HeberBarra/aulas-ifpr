CREATE DATABASE db_SistemaFilmesHeber;

USE db_SistemaFilmesHeber;

CREATE TABLE tbSala(
    numero_sala INT AUTO_INCREMENT,
    descricao_sala VARCHAR(20) NOT NULL,
    capacidade INT NOT NULL,
    CONSTRAINT pk_tbSala PRIMARY KEY (numero_sala)
);

CREATE TABLE tbDiretor (
    codigo_diretor INT AUTO_INCREMENT,
    nome_diretor VARCHAR(20) NOT NULL,
    CONSTRAINT pk_tbDiretor PRIMARY KEY (codigo_diretor)
);

CREATE TABLE tbFilme(
    codigo_filme INT AUTO_INCREMENT,
    nome_filme VARCHAR(50) NOT NULL,
    ano_lancamento INT NOT NULL,
    categoria_filme VARCHAR(20),
    codigo_diretor INT,
    CONSTRAINT pk_tbFilme PRIMARY KEY (codigo_filme),
    CONSTRAINT fk_tbDiretortbFilme FOREIGN KEY (codigo_filme)
    REFERENCES tbDiretor(codigo_diretor)
);

CREATE TABLE tbPremio(
    codigo_premio INT AUTO_INCREMENT,
    nome_premio VARCHAR(20) NOT NULL,
    ano_premiacao INT NOT NULL,
    codigo_filme INT,
    CONSTRAINT pk_tbPremio PRIMARY KEY (codigo_premio),
    CONSTRAINT fk_tbFilmetbPremio FOREIGN KEY (codigo_filme)
    REFERENCES tbFilme(codigo_filme)
);

CREATE TABLE tbSalaFilme(
    numero_sala INT,
    codigo_filme INT,
    data DATE,
    horario TIME NOT NULL,
    CONSTRAINT pk_tbSalaFilme PRIMARY KEY (numero_sala, codigo_filme, data),
    CONSTRAINT fk_tbSalaSalaFilme FOREIGN KEY (numero_sala)
    REFERENCES tbSala(numero_sala),
    CONSTRAINT fk_tbFilmeSalaFilme FOREIGN KEY (codigo_filme)
    REFERENCES tbFilme(codigo_filme)
);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Primeira sala', 70);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Segunda sala', 75);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Terceira sala', 80);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Quarta sala', 75);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Quinta sala', 70);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Sexta sala', 75);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Sétima sala', 80);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Oitava sala', 150);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Nona sala', 150);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Décima sala', 150);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Décima primeira sala', 150);

INSERT INTO
    tbSala(descricao_sala, capacidade)
VALUES
    ('Décima segunda sala', 150);

INSERT INTO
    tbDiretor(nome_diretor)
VALUES
    ('Pedro Paulo Matos');

INSERT INTO
    tbDiretor(nome_diretor)
VALUES
    ('Severino Juca');

INSERT INTO
    tbDiretor(nome_diretor)
VALUES
    ('James Cameron');

INSERT INTO
    tbDiretor(nome_diretor)
VALUES
    ('Emma Tammi');

INSERT INTO
    tbDiretor(nome_diretor)
VALUES
    ('Jorge de Penha');

INSERT INTO tbFilme
    (nome_filme, ano_lancamento, categoria_filme, codigo_diretor)
VALUES
    ('Titanic', 1998, 'Romance', 3);

INSERT INTO tbFilme
    (nome_filme, ano_lancamento, categoria_filme, codigo_diretor)
VALUES
    ('Five Nights at Freddy\'s - O Pesadelo Sem Fim', 2023, 'Terror', 4);

INSERT INTO tbFilme
    (nome_filme, ano_lancamento, categoria_filme, codigo_diretor)
VALUES
    ('Avatar', 2009, 'Drama', 3);

INSERT INTO tbPremio
    (nome_premio, ano_premiacao, codigo_filme)
VALUES
    ('Melhor Filme', 1998, 1);

INSERT INTO tbPremio
    (nome_premio, ano_premiacao, codigo_filme)
VALUES
    ('Melhor Direção', 1998, 1);

INSERT INTO tbPremio
    (nome_premio, ano_premiacao, codigo_filme)
VALUES
    ('Efeitos Visuais', 1998, 1);

INSERT INTO tbSalaFilme
    (numero_sala, codigo_filme, data, horario)
VALUES
    (12, 2, '2010-11-15', '15:00');

INSERT INTO tbSalaFilme
    (numero_sala, codigo_filme, data, horario)
VALUES
    (12, 3, '2010-11-15', '12:00');

INSERT INTO tbSalaFilme
    (numero_sala, codigo_filme, data, horario)
VALUES
    (8, 1, '2011-12-15', '11:00');

# Atividade 3
SELECT tbDiretor.nome_diretor FROM tbDiretor;

# Atividade 4
SELECT tbFilme.nome_filme FROM tbFilme
      WHERE categoria_filme = 'Terror';

# Atividade 5
UPDATE tbSala SET
    capacidade = 200
WHERE numero_sala = 8;

# Atividade 6
UPDATE tbSalaFilme SET
    numero_sala = 8
WHERE numero_sala = 12 AND data = '2010-11-15';

# Atividade 7
UPDATE tbFilme SET
    codigo_diretor = null
WHERE codigo_diretor =
    (SELECT codigo_diretor FROM tbDiretor
    WHERE nome_diretor = 'Pedro Paulo Matos');

DELETE FROM tbFilme WHERE
    codigo_diretor =
    (SELECT codigo_diretor FROM tbDiretor
    WHERE nome_diretor = 'Pedro Paulo Matos');

# Atividade 8
SELECT tbFilme.nome_filme
FROM tbFilme INNER JOIN tbDiretor ON tbFilme.codigo_diretor = tbDiretor.codigo_diretor
WHERE tbDiretor.nome_diretor = 'Jorge da Penha';

# Atividade 9
SELECT tbSalaFilme.horario, tbFilme.nome_filme
FROM tbSalaFilme INNER JOIN tbFilme ON tbSalaFilme.codigo_filme = tbFilme.codigo_filme
WHERE tbSalaFilme.data = '2010-04-20';

# Atividade 10
SELECT tbPremio.*
FROM tbPremio INNER JOIN tbFilme ON tbPremio.codigo_filme = tbFilme.codigo_filme
WHERE tbFilme.nome_filme = 'Titanic';

# Atividade 11
SELECT tbFilme.nome_filme
FROM tbFilme INNER JOIN tbSalaFilme ON tbFilme.codigo_filme = tbSalaFilme.codigo_filme
INNER JOIN tbSala ON tbSalaFilme.numero_sala = tbSala.numero_sala
WHERE tbSala.capacidade > 200;

# Atividade 12
SELECT tbFilme.nome_filme, tbSala.numero_sala, tbSalaFilme.horario
FROM tbFilme INNER JOIN tbSalaFilme ON tbFilme.codigo_filme = tbSalaFilme.codigo_filme
INNER JOIN tbSala ON tbSalaFilme.numero_sala = tbSala.numero_sala
WHERE tbSalaFilme.data BETWEEN '2010-05-01' AND '2010-05-31';

# Atividade 13
SELECT tbFilme.nome_filme
FROM tbFilme INNER JOIN tbPremio ON tbFilme.codigo_filme = tbPremio.codigo_filme
WHERE tbPremio.nome_premio = 'Melhor Diretor' AND
      tbPremio.ano_premiacao BETWEEN 2007 AND 2010;

# Atividade 14
SELECT tbFilme.nome_filme
FROM tbFilme INNER JOIN tbSalaFilme ON tbFilme.codigo_filme = tbSalaFilme.codigo_filme
WHERE tbFilme.categoria_filme = 'Comédia' AND
      tbSalaFilme.data BETWEEN '2010-06-01' AND '2010-06-31';

# Atividade 15
SELECT tbSala.descricao_sala, tbFilme.nome_filme, tbFilme.categoria_filme
FROM tbFilme INNER JOIN tbDiretor ON tbFilme.codigo_diretor = tbDiretor.codigo_diretor
INNER JOIN tbSalaFilme ON tbFilme.codigo_filme = tbSalaFilme.codigo_filme
INNER JOIN tbSala ON tbSalaFilme.numero_sala = tbSala.numero_sala
WHERE tbDiretor.nome_diretor = 'Severino Juca';
