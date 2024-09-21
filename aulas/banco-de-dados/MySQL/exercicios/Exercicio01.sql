CREATE DATABASE IF NOT EXISTS db_SistemaBancario;

USE db_SistemaBancario;

CREATE TABLE IF NOT EXISTS tbAgencia (
	
	cod_Ag INT,
	nome_Ag VARCHAR(25),
	cidade_Ag VARCHAR(25),
	fundos DOUBLE,
	CONSTRAINT pk_Agencia PRIMARY KEY(cod_Ag),
	CONSTRAINT un_NOMEtbAgencia UNIQUE (nome_Ag)

);

CREATE TABLE IF NOT EXISTS tbCliente (

	cod_Cli INT AUTO_INCREMENT,
	nome_Cli VARCHAR(25),
	cidade_Cli VARCHAR(25),
	rua_Cli VARCHAR(25),
	CONSTRAINT pk_Cliente PRIMARY KEY(cod_Cli)

);

CREATE TABLE IF NOT EXISTS tbConta (

	num_conta INT,
	cod_Ag INT,
	saldo DOUBLE,
	CONSTRAINT pk_Conta PRIMARY KEY(num_conta),
	CONSTRAINT fk_tbAgenciaConta FOREIGN KEY (cod_Ag)
	REFERENCES tbAgencia(cod_Ag)
	ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE IF NOT EXISTS tbEmprestimo (

	num_Emp INT AUTO_INCREMENT,
	cod_Ag INT,
	total DOUBLE,
	CONSTRAINT pk_Emprestimo PRIMARY KEY (num_Emp),
	CONSTRAINT fk_tbAgenciatbEmprestimo FOREIGN KEY (cod_Ag)
	REFERENCES tbAgencia(cod_Ag)
	ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE IF NOT EXISTS tbDepositante (

	num_Conta INT,
	cod_Cliente INT,
	CONSTRAINT pk_Depositante PRIMARY KEY (num_Conta, cod_Cliente),
	CONSTRAINT fk_tbContatbDepositante FOREIGN KEY (num_Conta)
	REFERENCES tbConta(num_Conta)
	ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_tbClientetbDepositante FOREIGN KEY (cod_Cliente)
	REFERENCES tbCliente(cod_Cli)
	ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE IF NOT EXISTS tbDevedor (

	cod_Cliente INT,
	num_Emp INT,
	CONSTRAINT pk_Devedor PRIMARY KEY (cod_Cliente, num_Emp),
	CONSTRAINT fk_tbClientetbDevedor FOREIGN KEY (cod_Cliente)
	REFERENCES tbCliente(cod_Cli)
	ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_tbEmprestimotbDevedor FOREIGN KEY(num_Emp)
	REFERENCES tbEmprestimo(num_Emp)
	ON DELETE CASCADE ON UPDATE CASCADE

);

