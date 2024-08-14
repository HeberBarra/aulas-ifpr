package conta;

import cliente.Cliente;

import java.math.BigDecimal;

public class ContaEspecial extends ContaCorrente {

    private BigDecimal limite;


    @Override
    public void sacar(BigDecimal valor) {

        if (limite.add(saldo).compareTo(valor) < 0) {
            System.out.println("Limite insuficiente. Cancelando operação...");
            return;
        }

        saldo = saldo.subtract(valor);
    }

    @Override
    public void transferir(BigDecimal valor, ContaCorrente contaRemetente) {

        if (limite.add(saldo).compareTo(valor) < 0) {
            System.out.println("Limite insuficiente. Cancelando operação...");
            return;
        }

        saldo = saldo.subtract(valor);
        contaRemetente.setSaldo(contaRemetente.getSaldo().add(valor));
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

}
