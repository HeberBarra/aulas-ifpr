CREATE DATABASE db_ifpr;

USE db_ifpr;

CREATE TABLE tbEndereco (
    id INT AUTO_INCREMENT,
    logradouro VARCHAR(49) NOT NULL,
    cep VARCHAR(7) NOT NULL,
    numero VARCHAR(49) NOT NULL,
    CONSTRAINT pk_tbEndereco PRIMARY KEY (id)
);

CREATE TABLE tbUsuario (

    id INT AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    cpf INT NOT NULL,
    senha TEXT NOT NULL,
    endereco INT NOT NULL,
    CONSTRAINT pk_tbUsuario PRIMARY KEY(id),
    CONSTRAINT un_NometbUsuario CHECK (nome),
    CONSTRAINT un_CpftbUsuario CHECK (cpf),
    CONSTRAINT fk_tbEnderecotbUsuario FOREIGN KEY (endereco)
    REFERENCES tbEndereco(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

SELECT * FROM tbEndereco;