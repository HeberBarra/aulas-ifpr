CREATE DATABASE db_SistemaBancarioHeber;

USE db_SistemaBancarioHeber;

CREATE TABLE tbAgencia (
	cod_Ag INT,
    nome_Ag VARCHAR(25) NOT NULL,
    cidade_Ag VARCHAR(25),
    fundos DOUBLE NOT NULL,
    CONSTRAINT pk_tbAgencia PRIMARY KEY(cod_Ag),
    CONSTRAINT un_NOMEtbAgencia UNIQUE (nome_Ag)
);

CREATE TABLE tbCliente (
	cod_Cli INT AUTO_INCREMENT,
    nome_Cli VARCHAR(25) NOT NULL,
    cidade_Cli VARCHAR(25) NOT NULL,
    rua_Cli VARCHAR(35) NOT NULL,
    CONSTRAINT pk_tbCliente PRIMARY KEY(cod_Cli)
);

CREATE TABLE tbConta (
	num_Conta INT AUTO_INCREMENT,
    cod_Ag INT,
    saldo DOUBLE NOT NULL,
    CONSTRAINT pk_tbConta PRIMARY KEY(num_Conta),
    CONSTRAINT fk_tbAgenciatbConta FOREIGN KEY (cod_Ag) REFERENCES tbAgencia(cod_Ag)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tbEmprestimo (
	num_Emp INT AUTO_INCREMENT,
    cod_Ag INT,
    total DOUBLE NOT NULL,
    CONSTRAINT pk_tbEmprestimo PRIMARY KEY (num_Emp),
    CONSTRAINT fk_tbAgenciatbEmprestimo FOREIGN KEY (cod_Ag) REFERENCES tbAgencia(cod_Ag)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tbDepositante (
	num_Conta INT,
    cod_Cli INT,
    CONSTRAINT pk_tbDepositante PRIMARY KEY(num_Conta, cod_Cli),
    CONSTRAINT fk_tbContatbDepositante FOREIGN KEY (num_Conta) REFERENCES tbConta(num_Conta)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tbClientetbDepositante FOREIGN KEY (cod_Cli) REFERENCES tbCliente(cod_Cli)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tbDevedor (
	cod_Cli INT,
    num_Emp INT,
    CONSTRAINT pk_tbDevedor PRIMARY KEY(cod_Cli, num_Emp),
    CONSTRAINT fk_tbClientetbDevedor FOREIGN KEY (cod_Cli) REFERENCES tbCliente(cod_Cli)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tbEmprestimotbDevedor FOREIGN KEY (num_Emp) REFERENCES tbEmprestimo(num_Emp)
    ON DELETE CASCADE ON UPDATE CASCADE
);

/*Inserção de Dados*/

/*tbAgencia*/

INSERT INTO tbAgencia(cod_Ag, nome_Ag, cidade_Ag, fundos)
VALUES (15, 'Boqueirão', 'Curitiba', 1500.00);

INSERT INTO tbAgencia(cod_Ag, nome_Ag, cidade_Ag, fundos)
VALUES (21, 'Portão', 'Curitiba', 1300.75);

INSERT INTO tbAgencia(cod_Ag, nome_Ag, cidade_Ag, fundos)
VALUES (18, 'Xaxim', 'Curitiba', 1200.00);

INSERT INTO tbAgencia(cod_Ag, nome_Ag, cidade_Ag, fundos)
VALUES (11, 'Hauer', 'Curitiba', 1100.00);

INSERT INTO tbAgencia(cod_Ag, nome_Ag, cidade_Ag, fundos)
VALUES (50, 'Santa Cândida', 'Curitiba', 1800.00);

SELECT tbAgencia.* FROM tbAgencia;

/*tbCliente*/

INSERT INTO tbCliente (nome_Cli, cidade_Cli, rua_Cli)
VALUES ('Heber', 'São José dos Pinhais', 'Joinville');

INSERT INTO tbCliente (nome_Cli, cidade_Cli, rua_Cli)
VALUES ('João', 'Itaperuçu', 'Antônio');

INSERT INTO tbCliente (nome_Cli, cidade_Cli, rua_Cli)
VALUES ('Matheus', 'Curitiba', 'Marechal Deodoro da Fonseca');

INSERT INTO tbCliente (nome_Cli, cidade_Cli, rua_Cli)
VALUES ('Rafael', 'Curitiba', 'Marechal Floriano Peixoto');

INSERT INTO tbCliente (nome_Cli, cidade_Cli, rua_Cli)
VALUES ('Samuel', 'Curitiba', 'Marechal Deodoro');

SELECT tbCliente.* FROM tbCliente;

/*tbConta*/

INSERT INTO tbConta (cod_Ag, saldo)
VALUES (11, 200);

INSERT INTO tbConta (cod_Ag, saldo)
VALUES (15, 300);

INSERT INTO tbConta (cod_Ag, saldo)
VALUES (18, 400);

INSERT INTO tbConta (cod_Ag, saldo)
VALUES (21, 500);

INSERT INTO tbConta (cod_Ag, saldo)
VALUES (50, 100);

SELECT tbConta.* FROM tbConta;

/*tbEmprestimo*/

INSERT INTO tbEmprestimo (cod_Ag, total)
VALUES (11, 100);

INSERT INTO tbEmprestimo (cod_Ag, total)
VALUES (15, 200);

INSERT INTO tbEmprestimo (cod_Ag, total)
VALUES (18, 300);

INSERT INTO tbEmprestimo (cod_Ag, total)
VALUES (21, 400);

INSERT INTO tbEmprestimo (cod_Ag, total)
VALUES (50, 500);

SELECT tbEmprestimo.* FROM tbEmprestimo;

/*tbDepositante*/

INSERT INTO tbDepositante (num_Conta, cod_Cli)
VALUES (1, 1);

INSERT INTO tbDepositante (num_Conta, cod_Cli)
VALUES (2, 3);

INSERT INTO tbDepositante (num_Conta, cod_Cli)
VALUES (3, 2);

INSERT INTO tbDepositante (num_Conta, cod_Cli)
VALUES (4, 5);

INSERT INTO tbDepositante (num_Conta, cod_Cli)
VALUES (5, 4);

SELECT tbDepositante.* FROM tbDepositante;

/*tbDevedor*/

INSERT INTO tbDevedor (cod_Cli, num_Emp)
VALUES (1, 2);

INSERT INTO tbDevedor (cod_Cli, num_Emp)
VALUES (2, 1);

INSERT INTO tbDevedor (cod_Cli, num_Emp)
VALUES (3, 2);

INSERT INTO tbDevedor (cod_Cli, num_Emp)
VALUES (4, 5);

INSERT INTO tbDevedor (cod_Cli, num_Emp)
VALUES (5, 1);

SELECT tbDevedor.* FROM tbDevedor;
