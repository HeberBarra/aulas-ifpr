package conta;

import java.math.BigDecimal;

public class ContaPoupanca extends ContaCorrente {

    private BigDecimal taxaJuros;

    public ContaPoupanca() {
        agencia = "2537-2";
    }

    @Override
    public void depositar(BigDecimal valor) {
    }

    @Override
    public void sacar(BigDecimal valor) {

        if (saldo.compareTo(valor) < 0) {
            System.out.println("Saldo insuficiente. Não é possível realizar o saque.");
            return;
        }

        saldo = saldo.subtract(valor);

    }

}
