package equipamento.scanner;

import equipamento.Equipamento;

public class Scanner extends Equipamento {

    private int velocidadeDigitalizacao;
    private TipoScanner tipo;

    @Override
    public void iniciarOperacao() {
        System.out.println("Scanear");
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

    public int getVelocidadeDigitalizacao() {
        return velocidadeDigitalizacao;
    }

    public void setVelocidadeDigitalizacao(int velocidadeDigitalizacao) {
        this.velocidadeDigitalizacao = velocidadeDigitalizacao;
    }

    public TipoScanner getTipo() {
        return tipo;
    }

    public void setTipo(TipoScanner tipo) {
        this.tipo = tipo;
    }

}
