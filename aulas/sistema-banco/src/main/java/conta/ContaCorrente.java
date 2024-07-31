package conta;

import java.math.BigDecimal;

public abstract class ContaCorrente {

    protected int id;
    protected String agencia;
    protected int numero;
    protected BigDecimal saldo;

    public ContaCorrente() {
        saldo = new BigDecimal(0);
    }

    public void depositar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }

    public abstract void sacar(BigDecimal valor);

}
