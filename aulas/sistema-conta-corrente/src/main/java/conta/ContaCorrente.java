package conta;

import cliente.Cliente;
import java.math.BigDecimal;

public abstract class ContaCorrente {

    protected int id;
    protected int numero;
    protected String agencia;
    protected BigDecimal saldo;
    protected Cliente proprierario;

    public ContaCorrente() {
        saldo = new BigDecimal(0);
    }

    public void mostrarInformacoes() {
        System.out.println("=============================");
        System.out.printf("ID: %d%n", id);
        System.out.printf("NÚMERO: %d%n", numero);
        System.out.printf("AGÊNCIA: %s%n", agencia);
        System.out.printf("SALDO: R$%s%n", saldo);
        System.out.printf("PROPRIETÁRIO: %s%n", proprierario.getNome());
    }

    public abstract void sacar(BigDecimal valor);

    public void depositar(BigDecimal valor) {
        if (valor.compareTo(new BigDecimal(0)) < 1) {
            System.out.println("O valor do depósito deve ser positivo. Cancelando a operação...");
        }

        saldo = saldo.add(valor);
    }

    public abstract void transferir(BigDecimal valor, ContaCorrente contaRemetente);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Cliente getProprierario() {
        return proprierario;
    }

    public void setProprierario(Cliente proprierario) {
        this.proprierario = proprierario;
    }
}
