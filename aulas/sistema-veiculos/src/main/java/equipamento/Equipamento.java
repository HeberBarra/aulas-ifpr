package equipamento;

import gerenciavel.Gerenciavel;

public abstract class Equipamento implements Gerenciavel {

    protected int id;
    protected String nome;
    protected String marca;
    protected String modelo;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void mostrarIdentificacao() {
        System.out.printf("ID: %d NOME: %s MARCA: %s MODELo: %s%n", id, nome, marca, modelo);
    }

    public abstract void iniciarOperacao();

}
