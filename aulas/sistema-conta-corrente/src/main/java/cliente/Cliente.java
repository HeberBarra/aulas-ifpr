package cliente;

import conta.ContaCorrente;

import java.util.List;

public abstract class Cliente {

    protected int id;
    protected long rg;
    protected String nome;
    protected String login;
    protected String senha;
    protected List<ContaCorrente> contas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRg() {
        return rg;
    }

    public void setRg(long rg) {
        this.rg = rg;
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
