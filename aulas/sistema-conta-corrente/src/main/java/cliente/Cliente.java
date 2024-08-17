package cliente;

import conta.ContaCorrente;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {

    protected int id;
    protected String nome;
    protected String login;
    protected String senha;
    protected List<ContaCorrente> contas;
    protected String mascara;
    protected int quantidadeDigitosMascara;

    public Cliente() {
        contas = new ArrayList<>();
    }

    public void mostrarInformacoes() {
        System.out.println("=======================");
        System.out.printf("ID: %d%n", id);
        System.out.printf("NOME: %s%n", nome);
        System.out.printf("LOGIN: %s%n", login);
        System.out.printf("QTD CONTAS: %d%n", contas.size());
    }

    protected abstract String formatar(long valor);

    protected String pad(String valor) {
        StringBuilder stringBuilder = new StringBuilder(valor);
        while(stringBuilder.length() != quantidadeDigitosMascara) {
            stringBuilder.insert(0, "0");
        }

        return stringBuilder.toString();
    }

    protected void contarDigitosMascara() {
        quantidadeDigitosMascara = 0;
        for (char character: mascara.toCharArray()) {
            if (character == '#') {
                quantidadeDigitosMascara++;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<ContaCorrente> getContas() {
        return contas;
    }

    public void setContas(List<ContaCorrente> contas) {
        this.contas = contas;
    }

}
