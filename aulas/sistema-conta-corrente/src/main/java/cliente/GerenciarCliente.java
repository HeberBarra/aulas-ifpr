package cliente;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GerenciarCliente {

    private AtomicInteger geradorCodigo;
    private Hashtable<Integer, Cliente> clientesDB;

    public GerenciarCliente() {
        geradorCodigo = new AtomicInteger();
        clientesDB = new Hashtable<>();
    }

    public void add(Cliente cliente) {
        int id = geradorCodigo.incrementAndGet();
        cliente.setId(id);
        clientesDB.put(id, cliente);
    }

    public List<Cliente> list() {
        return new ArrayList<>(clientesDB.values());
    }

    public void update(Cliente cliente) {
        clientesDB.replace(cliente.getId(), cliente);
    }

    public void delete(Cliente cliente) {
        clientesDB.remove(cliente.getId());
    }

    public Cliente findById(int id) {
        for (Cliente cliente: clientesDB.values()) {
            if (cliente.id == id) {
                return cliente;
            }
        }

        return null;
    }

}
