package conta;

import java.math.BigDecimal;

public class ContaPoupanca extends ContaCorrente {

    private String extrato = "";

    @Override
    public void mostrarInformacoes() {
        super.mostrarInformacoes();
        System.out.printf("EXTRATO: %n%s%n", extrato);
    }

    @Override
    public void depositar(BigDecimal valor) {
        super.depositar(valor);
        String linhaExtrato = "DEPÓSITO: R$%s%n".formatted(valor);
        extrato = extrato + linhaExtrato;
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor.compareTo(new BigDecimal(0)) < 1) {
            System.out.println("O valor do saque deve ser positivo. Cancelando a operação...");
            return;
        }

        if (saldo.compareTo(valor) < 0) {
            System.out.println("Saldo insuficiente. Cancelando a operação...");
            return;
        }

        saldo = saldo.subtract(valor);

        String linhaExtrato = "SAQUE: R$%s%n".formatted(valor);
        extrato = extrato + linhaExtrato;
    }

    @Override
    public void transferir(BigDecimal valor, ContaCorrente contaRemetente) {
        if (valor.compareTo(new BigDecimal(0)) < 1) {
            System.out.println("O valor da transferência deve ser positivo. Cancelando a operação...");
            return;
        }

        if (saldo.compareTo(valor) < 0) {
            System.out.println("Saldo insuficiente. Cancelando a operação...");
            return;
        }

        saldo = saldo.subtract(valor);
        contaRemetente.setSaldo(contaRemetente.getSaldo().add(valor));
        String linhaExtrato = "TRANSFERÊNCIA: R$%s para %s%n".formatted(valor, contaRemetente.getProprierario().getNome());
        extrato = extrato + linhaExtrato;
    }

    public String getExtrato() {
        return extrato;
    }

    public void setExtrato(String extrato) {
        this.extrato = extrato;
    }

}
