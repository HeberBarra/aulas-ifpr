package conta;

import cliente.Cliente;

import java.math.BigDecimal;

public abstract class ContaCorrente {

    protected int id;
    protected int numero;
    protected String agencia;
    protected BigDecimal saldo;

    public abstract void sacar(BigDecimal valor);

    public void depositar(BigDecimal valor) {
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

}
