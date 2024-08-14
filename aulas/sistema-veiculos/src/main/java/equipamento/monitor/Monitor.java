package equipamento.monitor;

import equipamento.Equipamento;

import java.awt.Dimension;

public class Monitor extends Equipamento {

    private Dimension resolucao;
    private boolean IPS;

    @Override
    public void iniciarOperacao() {
        System.out.println("Mostrar algo");
    }

    @Override
    public void ligar() {

    }

    @Override
    public void desligar() {

    }

    @Override
    public void mostrarStatus() {

    }

    public Dimension getResolucao() {
        return resolucao;
    }

    public void setResolucao(Dimension resolucao) {
        this.resolucao = resolucao;
    }

    public boolean isIPS() {
        return IPS;
    }

    public void setIPS(boolean IPS) {
        this.IPS = IPS;
    }

}
