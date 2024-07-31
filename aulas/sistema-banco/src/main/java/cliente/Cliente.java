package cliente;

import conta.ContaCorrente;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private int id;
    private String nome;
    private long cpf;
    private List<ContaCorrente> contas;

    public Cliente() {
        contas = new ArrayList<>();
    }

    public void addConta(ContaCorrente conta) {
        contas.add(conta);
    }

}
