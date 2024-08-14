package conta;

import cliente.Cliente;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GerenciarConta {

    private AtomicInteger geradorCodigo;
    private Hashtable<Integer, ContaCorrente> contasBD;

    public GerenciarConta() {
        geradorCodigo = new AtomicInteger();
        contasBD = new Hashtable<>();
    }

    public void add(Cliente cliente, ContaCorrente conta) {
        int id = geradorCodigo.incrementAndGet();
        conta.setId(id);
        cliente.getContas().add(conta);
        contasBD.put(id, conta);
    }

    public List<ContaCorrente> list() {
        return new ArrayList<>(contasBD.values());
    }

    public void update(Cliente cliente, ContaCorrente conta) {
        List<ContaCorrente> contas = cliente.getContas();

        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).equals(conta)) {
                contas.set(i, conta);
            }
        }

        cliente.setContas(contas);
        contasBD.replace(conta.getId(), conta);
    }

    public void delete(Cliente cliente, ContaCorrente conta) {
        cliente.getContas().remove(conta);
    }

    public ContaCorrente findBy(int id) {
        for (ContaCorrente conta: list()) {
            if (conta.getId() == id) {
                return conta;
            }
        }

        return null;
    }

}
