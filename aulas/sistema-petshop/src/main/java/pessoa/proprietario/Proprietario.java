package pessoa.proprietario;

import pessoa.Pessoa;

import java.time.LocalDate;

public class Proprietario extends Pessoa {

    private LocalDate dataNascimento;
    private SexoProprietario sexo;

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public SexoProprietario getSexo() {
        return sexo;
    }

    public void setSexo(SexoProprietario sexo) {
        this.sexo = sexo;
    }

}
