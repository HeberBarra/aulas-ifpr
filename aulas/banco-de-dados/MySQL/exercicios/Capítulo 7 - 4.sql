CREATE DATABASE db_SistemaEmpresaHeber;

USE db_SistemaEmpresaHeber;

CREATE TABLE tbCompanhia (
	cod_companhia INT AUTO_INCREMENT,
    nome_companhia VARCHAR(50) NOT NULL,
    cidade_companhia VARCHAR(50) NOT NULL,
    CONSTRAINT pk_tbCompanhia PRIMARY KEY (cod_companhia)
);

CREATE TABLE tbGerente (
	cod_gerente INT AUTO_INCREMENT,
    nome_gerente VARCHAR(50) NOT NULL,
    CONSTRAINT pk_tbGerente PRIMARY KEY (cod_gerente)
);



CREATE TABLE tbEmpregado (
    cod_emp INT AUTO_INCREMENT,
    nome_emp VARCHAR(50) NOT NULL,
    rua_emp VARCHAR(60) NOT NULL,
    cidade_emp VARCHAR(30) NOT NULL,
    fone_emp NUMERIC(11) NOT NULL,
    cod_gerente INT,
    CONSTRAINT pk_tbEmpregado PRIMARY KEY(cod_emp),
    CONSTRAINT un_FonetbEmpregado UNIQUE (fone_emp),
    CONSTRAINT fk_tbGerentetbEmpregado FOREIGN KEY (cod_gerente)
    REFERENCES tbGerente(cod_gerente)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tbTrabalha (
	cod_emp INT,
    cod_companhia INT,
    salario DOUBLE,
    CONSTRAINT pk_tbTrabalha PRIMARY KEY (cod_emp, cod_companhia)
);
