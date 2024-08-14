package conta;

import java.math.BigDecimal;

public class ContaPoupanca extends ContaCorrente {

    private String extrato;

    @Override
    public void sacar(BigDecimal valor) {
        if (saldo.compareTo(valor) < 0) {
            System.out.println("Saldo insuficiente. Cancelando operação...");
            return;
        }

        saldo = saldo.subtract(valor);
    }

    @Override
    public void transferir(BigDecimal valor, ContaCorrente contaRemetente) {
        if (saldo.compareTo(valor) < 0) {
            System.out.println("Saldo insuficiente. Cancelando operação...");
            return;
        }

        saldo = saldo.subtract(valor);
        contaRemetente.setSaldo(contaRemetente.getSaldo().add(valor));
    }

    public String getExtrato() {
        return extrato;
    }

    public void setExtrato(String extrato) {
        this.extrato = extrato;
    }

}
