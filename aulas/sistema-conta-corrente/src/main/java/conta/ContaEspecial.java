package conta;

import java.math.BigDecimal;

public class ContaEspecial extends ContaCorrente {

    private static final int LIMITE_PADRAO = 1000;
    private BigDecimal limite;

    public ContaEspecial() {
        super();
        limite = new BigDecimal(LIMITE_PADRAO);
    }

    @Override
    public void mostrarInformacoes() {
        super.mostrarInformacoes();
        System.out.printf("LIMITE: %s%n", limite);
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor.compareTo(new BigDecimal(0)) < 1) {
            System.out.println("O valor do saque deve ser positivo. Cancelando a operação...");
            return;
        }

        if (limite.add(saldo).compareTo(valor) < 0) {
            System.out.println("Limite insuficiente. Cancelando a operação...");
            return;
        }

        saldo = saldo.subtract(valor);
    }

    @Override
    public void transferir(BigDecimal valor, ContaCorrente contaRemetente) {
        if (valor.compareTo(new BigDecimal(0)) < 1) {
            System.out.println("O valor da trasferência deve ser positivo. Cancelando a operação...");
            return;
        }

        if (limite.add(saldo).compareTo(valor) < 0) {
            System.out.println("Limite insuficiente. Cancelando a operação...");
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
