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
	cod_Cli INT,
    nome_Cli VARCHAR(25) NOT NULL,
    cidade_Cli VARCHAR(25) NOT NULL,
    rua_Cli VARCHAR(35) NOT NULL,
    CONSTRAINT pk_tbCliente PRIMARY KEY(cod_Cli)
);

CREATE TABLE tbConta (
	num_Conta INT,
    cod_Ag INT,
    saldo DOUBLE NOT NULL,
    CONSTRAINT pk_tbConta PRIMARY KEY(num_Conta),
    CONSTRAINT fk_tbAgenciatbConta FOREIGN KEY (cod_Ag) REFERENCES tbAgencia(cod_Ag)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tbEmprestimo (
	num_Emp INT,
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
