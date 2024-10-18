CREATE DATABASE bdEmpregadoHeber;

USE bdEmpregadoHeber;

CREATE TABLE tbProjeto(
    nome_proj VARCHAR(60) NOT NULL,
    numero_proj INT,
    localizacao VARCHAR(100),
    CONSTRAINT pk_tbProjeto PRIMARY KEY(numero_proj)
);

CREATE TABLE tbDepartamento(
    nome_depto VARCHAR(60) NOT NULL,
    numero_depto INT AUTO_INCREMENT,
    rgGerente_depto BIGINT,
    CONSTRAINT pk_tbDepartamento PRIMARY KEY (numero_depto)
);

CREATE TABLE tbEmpregado(
    nome_emp VARCHAR(60) NOT NULL,
    RG_emp BIGINT,
    CPF_emp BIGINT,
    numDepto_emp INT,
    salario_emp DOUBLE,
    cidade_emp VARCHAR(50),
    CONSTRAINT pk_tbEmpregado PRIMARY KEY (RG_emp),
    CONSTRAINT fk_tbDepartamentotbEmpregado FOREIGN KEY (numDepto_emp)
    REFERENCES tbDepartamento(numero_depto),
    CONSTRAINT un_CPF_emptbEmpregado UNIQUE (CPF_emp)
);

ALTER TABLE tbDepartamento
ADD CONSTRAINT fk_tbEmpregadotbDepartamento FOREIGN KEY(rgGerente_depto)
REFERENCES tbEmpregado(RG_emp);

CREATE TABLE tbDependentes(
    rgResponsavel_dep BIGINT,
    nome_dep VARCHAR(50),
    dataNascimento_dep DATE,
    genero_dep CHAR(1),
    CONSTRAINT pk_tbDependentes PRIMARY KEY (rgResponsavel_dep, nome_dep),
    CONSTRAINT fk_tbEmpregadotbDependentes FOREIGN KEY (rgResponsavel_dep)
    REFERENCES tbEmpregado(RG_emp),
    CONSTRAINT ck_GenerotbDependentes CHECK (genero_dep IN ('M', 'F', 'O'))
);

CREATE TABLE tbDepartamentoProjeto(
    numero_depto INT,
    numero_proj INT,
    CONSTRAINT pk_tbDepartamentoProjeto PRIMARY KEY (numero_depto, numero_proj),
    CONSTRAINT fk_tbDepartamentotbDepartamentoProjeto FOREIGN KEY (numero_depto)
    REFERENCES tbDepartamento(numero_depto),
    CONSTRAINT fk_tbProjetotbDepartamentoProjeto FOREIGN KEY (numero_proj)
    REFERENCES tbProjeto(numero_proj)
);

CREATE TABLE tbEmpregadoProjeto(
    rg_emp BIGINT,
    numero_proj INT,
    horas_proj INT,
    CONSTRAINT pk_tbEmpregadoProjeto PRIMARY KEY (rg_emp, numero_proj),
    CONSTRAINT fk_tbEmpregadotbEmpregadoProjeto FOREIGN KEY (rg_emp)
    REFERENCES tbEmpregado(RG_emp),
    CONSTRAINT fk_tbProjetotbEmpregadoProjeto FOREIGN KEY (numero_proj)
    REFERENCES tbProjeto(numero_proj)
);

# Atividade 3
## tbDepartamento
INSERT INTO tbDepartamento
    (nome_depto)
VALUES
    ('G1');

INSERT INTO tbDepartamento
    (nome_depto)
VALUES
    ('G2');

INSERT INTO tbDepartamento
    (nome_depto)
VALUES
    ('G3');

INSERT INTO tbDepartamento
    (nome_depto)
VALUES
    ('G4');

INSERT INTO tbDepartamento
    (nome_depto)
VALUES
    ('G5');

##tbEmpregado
INSERT INTO tbEmpregado
    (nome_emp, RG_emp, CPF_emp, numDepto_emp, salario_emp, cidade_emp)
VALUES
    ('João Gabriel', 121212, 212121, 1, 1500,'Curitiba');

INSERT INTO tbEmpregado
    (nome_emp, RG_emp, CPF_emp, numDepto_emp, salario_emp, cidade_emp)
VALUES
    ('Heber Ferreira Barra', 131313, 313131, 2, 1500,'Curitiba');

INSERT INTO tbEmpregado
    (nome_emp, RG_emp, CPF_emp, numDepto_emp, salario_emp, cidade_emp)
VALUES
    ('Matheus Jun Alves Matuda', 141414, 414141, 3, 1500,'Curitiba');

INSERT INTO tbEmpregado
    (nome_emp, RG_emp, CPF_emp, numDepto_emp, salario_emp, cidade_emp)
VALUES
    ('Matheus de Assis de Paula', 151515, 515151, 4, 1500,'Curitiba');

INSERT INTO tbEmpregado
    (nome_emp, RG_emp, CPF_emp, numDepto_emp, salario_emp, cidade_emp)
VALUES
    ('Rafael Juck', 161616, 616161, 5, 1500,'Curitiba');

INSERT INTO tbEmpregado
    (nome_emp, RG_emp, CPF_emp, numDepto_emp, salario_emp, cidade_emp)
VALUES
    ('Mário Sérgio Pereira Melo Júnior', 171717, 717171, 5, 4000, 'Curitiba');

UPDATE tbDepartamento SET
    rgGerente_depto = 121212
WHERE numero_depto = 1;

UPDATE tbDepartamento SET
    rgGerente_depto = 131313
WHERE numero_depto = 2;

UPDATE tbDepartamento SET
    rgGerente_depto = 141414
WHERE numero_depto = 3;

UPDATE tbDepartamento SET
    rgGerente_depto = 151515
WHERE numero_depto = 4;

UPDATE tbDepartamento SET
    rgGerente_depto = 161616
WHERE numero_depto = 5;

# tbDependentes
INSERT INTO tbDependentes
    (rgResponsavel_dep, nome_dep, dataNascimento_dep, genero_dep)
VALUES
    (121212, 'Antonella', '2033-07-12', 'F');

INSERT INTO tbDependentes
    (rgResponsavel_dep, nome_dep, dataNascimento_dep, genero_dep)
VALUES
    (121212, 'Francisco', '2034-06-12', 'M');

INSERT INTO tbDependentes
    (rgResponsavel_dep, nome_dep, dataNascimento_dep, genero_dep)
VALUES
    (131313, 'Aylla', '2034-05-23', 'F');

INSERT INTO tbDependentes
    (rgResponsavel_dep, nome_dep, dataNascimento_dep, genero_dep)
VALUES
    (131313, 'Liz', '2035-04-20', 'F');

INSERT INTO tbDependentes
    (rgResponsavel_dep, nome_dep, dataNascimento_dep, genero_dep)
VALUES
    (131313, 'Natanael', '2036-03-22', 'M');

# tbProjeto
INSERT INTO tbProjeto
    (numero_proj, nome_proj, localizacao)
VALUES
    (1, 'Primeiro Projeto', 'Curitiba');

INSERT INTO tbProjeto
    (numero_proj, nome_proj, localizacao)
VALUES
    (2, 'Segundo Projeto', 'São José dos Pinhais');

INSERT INTO tbProjeto
    (numero_proj, nome_proj, localizacao)
VALUES
    (3, 'Terceiro Projeto', 'Curitiba');

INSERT INTO tbProjeto
    (numero_proj, nome_proj, localizacao)
VALUES
    (4, 'Quarto Projeto', 'Colombo');

INSERT INTO tbProjeto
    (numero_proj, nome_proj, localizacao)
VALUES
    (5, 'Quinto Projeto', 'Curitiba');

# tbDepartamentoProjeto

INSERT INTO tbDepartamentoProjeto
    (numero_depto, numero_proj)
VALUES
    (1, 1);

INSERT INTO tbDepartamentoProjeto
    (numero_depto, numero_proj)
VALUES
    (2, 2);

INSERT INTO tbDepartamentoProjeto
    (numero_depto, numero_proj)
VALUES
    (3, 3);


INSERT INTO tbDepartamentoProjeto
    (numero_depto, numero_proj)
VALUES
    (4, 4);

INSERT INTO tbDepartamentoProjeto
    (numero_depto, numero_proj)
VALUES
    (5, 5);

# tbEmpregadoProjeto

INSERT INTO tbEmpregadoProjeto
    (rg_emp, numero_proj, horas_proj)
VALUES
    (121212, 1, 10);

INSERT INTO tbEmpregadoProjeto
    (rg_emp, numero_proj, horas_proj)
VALUES
    (131313, 2, 20);

INSERT INTO tbEmpregadoProjeto
    (rg_emp, numero_proj, horas_proj)
VALUES
    (141414, 3, 30);

INSERT INTO tbEmpregadoProjeto
    (rg_emp, numero_proj, horas_proj)
VALUES
    (151515, 4, 20);

INSERT INTO tbEmpregadoProjeto
    (rg_emp, numero_proj, horas_proj)
VALUES
    (161616, 5, 20);

# Atividade 4

SELECT tbEmpregado.* FROM tbEmpregado;

# Atividade 5

SELECT tbProjeto.nome_proj
FROM tbProjeto
WHERE localizacao = 'Curitiba';

# Atividade 6

SELECT tbEmpregado.nome_emp
FROM tbEmpregado
WHERE tbEmpregado.cidade_emp = 'São José dos Pinhais';

# Atividade 7
SELECT tbEmpregado.salario_emp
FROM tbEmpregado
WHERE tbEmpregado.cidade_emp = 'Curitiba';

# Atividade 8
SELECT tbDependentes.nome_dep
FROM tbDependentes
WHERE tbDependentes.dataNascimento_dep BETWEEN '2009-01-01' AND '2009-12-31';

# Atividade 9
SELECT tbEmpregado.nome_emp, tbEmpregado.cidade_emp
FROM tbEmpregado
WHERE tbEmpregado.salario_emp > 3000;

# Atividade 10
SELECT tbDepartamento.nome_depto, tbEmpregado.nome_emp
FROM tbDepartamento INNER JOIN tbEmpregado ON tbDepartamento.rgGerente_depto = tbEmpregado.RG_emp;

# Atividade 11
SELECT tbDepartamento.nome_depto
FROM tbDepartamento INNER JOIN tbDepartamentoProjeto ON tbDepartamento.numero_depto = tbDepartamentoProjeto.numero_depto
INNER JOIN tbProjeto ON tbDepartamentoProjeto.numero_proj = tbProjeto.numero_proj
WHERE tbProjeto.numero_proj = 2;

# Atividade 12
## Troquei 'informática' por 'G5'
SELECT tbEmpregado.nome_emp
FROM tbEmpregado INNER JOIN tbDepartamento ON tbEmpregado.numDepto_emp = tbDepartamento.numero_depto
WHERE tbDepartamento.nome_depto = 'G5';

# Atividade 13
SELECT tbEmpregado.nome_emp
FROM tbEmpregado INNER JOIN tbEmpregadoProjeto ON tbEmpregado.RG_emp = tbEmpregadoProjeto.rg_emp
INNER JOIN tbProjeto ON tbEmpregadoProjeto.numero_proj = tbProjeto.numero_proj
WHERE tbProjeto.numero_proj = 2;

# Atividade 14
UPDATE tbEmpregado
SET
    cidade_emp = 'São José dos Pinhais'
WHERE nome_emp = 'Heber Ferreira Barra';

# Atividade 15
UPDATE tbEmpregado
SET
    salario_emp = salario_emp * 1.07
WHERE salario_emp > 3200;

# Atividade 16
## Troquei 'Projeto Alfa' para 'Quinto Projeto'

ALTER TABLE tbDepartamentoProjeto
DROP FOREIGN KEY fk_tbProjetotbDepartamentoProjeto;

UPDATE tbDepartamentoProjeto
SET
    tbDepartamentoProjeto.numero_proj = 23
WHERE numero_proj = (SELECT tbProjeto.numero_proj
                    FROM tbProjeto
                    WHERE nome_proj = 'Quinto Projeto');

ALTER TABLE tbEmpregadoProjeto
DROP FOREIGN KEY fk_tbProjetotbEmpregadoProjeto;

UPDATE tbEmpregadoProjeto
SET
    tbEmpregadoProjeto.numero_proj = 23
WHERE numero_proj = (SELECT tbProjeto.numero_proj
                     FROM tbProjeto
                     WHERE nome_proj = 'Quinto Projeto');

UPDATE tbProjeto
SET
    numero_proj = 23
WHERE nome_proj = 'Quinto Projeto';


ALTER TABLE tbDepartamentoProjeto
ADD CONSTRAINT fk_tbProjetotbDepartamentoProjeto FOREIGN KEY (numero_proj)
REFERENCES tbProjeto(numero_proj);

ALTER TABLE tbEmpregadoProjeto
ADD CONSTRAINT fk_tbProjetotbEmpregadoProjeto FOREIGN KEY (numero_proj)
REFERENCES tbProjeto(numero_proj);

# Atividade 17
DELETE FROM tbDependentes
WHERE tbDependentes.rgResponsavel_dep = (SELECT tbEmpregado.RG_emp
                                         FROM tbEmpregado
                                         WHERE tbEmpregado.nome_emp = 'João Gabriel');

# Atividade 18
## Troquei 'Projeto Omega' por 'Quarto Projeto'
DELETE FROM tbDepartamentoProjeto
WHERE tbDepartamentoProjeto.numero_proj = (SELECT tbProjeto.numero_proj
                                           FROM tbProjeto
                                           WHERE tbProjeto.nome_proj = 'Quarto Projeto');

DELETE FROM tbEmpregadoProjeto
WHERE tbEmpregadoProjeto.numero_proj = (SELECT tbProjeto.numero_proj
                                        FROM tbProjeto
                                        WHERE tbProjeto.nome_proj = 'Quarto Projeto');

DELETE FROM tbProjeto
WHERE tbProjeto.nome_proj = 'Quarto Projeto';
