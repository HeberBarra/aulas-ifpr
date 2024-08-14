package veiculo.caminhao;

import veiculo.Veiculo;

public class Caminhao extends Veiculo {

    private int quantidadeEixos;
    private TipoCabine tipoCabine;

    @Override
    public void ligar() {

    }

    @Override
    public void desligar() {

    }

    @Override
    public void mostrarStatus() {

    }

    @Override
    public void efetuarRevisao() {

    }

    public int getQuantidadeEixos() {
        return quantidadeEixos;
    }

    public void setQuantidadeEixos(int quantidadeEixos) {
        this.quantidadeEixos = quantidadeEixos;
    }

    public TipoCabine getTipoCabine() {
        return tipoCabine;
    }

    public void setTipoCabine(TipoCabine tipoCabine) {
        this.tipoCabine = tipoCabine;
    }

}
