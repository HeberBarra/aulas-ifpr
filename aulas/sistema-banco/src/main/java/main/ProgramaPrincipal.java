package main;

import cliente.Cliente;
import conta.ContaCorrente;
import conta.ContaEspecial;
import conta.ContaPoupanca;

import java.math.BigDecimal;

public class ProgramaPrincipal {

    public static void main(String[] args) {
        Cliente paulo = new Cliente();

        ContaCorrente contaPoupanca = new ContaPoupanca();
        contaPoupanca.depositar(new BigDecimal(100));

        ContaCorrente contaEspecial = new ContaEspecial();
        contaEspecial.depositar(new BigDecimal(50));

        paulo.addConta(contaPoupanca);
        paulo.addConta(contaEspecial);

        contaPoupanca.sacar(new BigDecimal(15));
    }

}
