package conta;

import java.math.BigDecimal;

public class ContaEspecial extends ContaCorrente {

    private BigDecimal limite;

    @Override
    public void sacar(BigDecimal valor) {
        if (saldo.add(limite).compareTo(valor) < 0) {
            System.out.println("Limite insuficiente. Não é possível sacar");
            return;
        }

        saldo = saldo.subtract(valor);
    }
}
