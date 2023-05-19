package pessoa;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public abstract class Pessoa {

    protected String nome;

    protected String cpf;

    private LocalDate dataNascimento;

    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        if ((Calendar.getInstance().get(Calendar.YEAR) - dataNascimento.getYear()
            ) >= 18) {
            this.nome = nome;
            this.cpf = cpf;
            this.dataNascimento = dataNascimento;
        }else{
            throw new RuntimeException("Pessoa menor de idade!");
        }

    }

    public String getNome() {
        return nome;
    }
}
