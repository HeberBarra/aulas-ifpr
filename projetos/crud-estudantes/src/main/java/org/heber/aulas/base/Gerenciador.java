package org.heber.aulas.base;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Gerenciador<T extends Modelo> {

    protected AtomicInteger geradorCodigo;
    protected Hashtable<Integer, T> bancoDeDados;

    public Gerenciador() {
        bancoDeDados = new Hashtable<>();
        geradorCodigo = new AtomicInteger();
    }

    public void create(T modelo) {
        int novoCodigo = geradorCodigo.getAndIncrement();
        modelo.setCodigo(novoCodigo);
        bancoDeDados.put(novoCodigo, modelo);
    }

    public void update(T modelo) {
        bancoDeDados.replace(modelo.getCodigo(), modelo);
    }

    public List<T> list() {
        return new ArrayList<>(bancoDeDados.values());
    }

    public void remove(int codigo) {
        bancoDeDados.remove(codigo);
    }

    public T findById(int codigo) {
        return bancoDeDados.get(codigo);
    }

    public List<T> listByName(String nome) {
        List<T> modelos = new ArrayList<>();

        for (T modelo: list()) {
            if (modelo.getNome().toLowerCase().contains(nome.toLowerCase())) {
                modelos.add(modelo);
            }
        }

        return modelos;
    }

    public abstract String pegarInformacoes();

    public abstract void lerInformacoes(String linhaCsv);

    public void atualizarIndiceGerador() {
        int ultimoIndice = list().getLast().getCodigo();
        geradorCodigo.set(ultimoIndice + 1);
    }

}
