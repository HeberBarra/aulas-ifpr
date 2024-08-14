package equipamento.impressora;

import equipamento.Equipamento;

public class Impressora extends Equipamento {

    private int dpi;
    private int ppm;

    @Override
    public void iniciarOperacao() {
        System.out.println("Imprimir");
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

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public int getPpm() {
        return ppm;
    }

    public void setPpm(int ppm) {
        this.ppm = ppm;
    }

}
