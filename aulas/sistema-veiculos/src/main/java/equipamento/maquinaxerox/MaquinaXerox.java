package equipamento.maquinaxerox;

import equipamento.Equipamento;

public class MaquinaXerox extends Equipamento {

    private int capacidadeFolhas;
    private TipoMaquinaXerox tipo;

    @Override
    public void iniciarOperacao() {
        System.out.println("Copiar");
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

}
