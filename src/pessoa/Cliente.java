package pessoa;

import conta.Conta;
import conta.ContaCorrente;
import conta.ContaPoupanca;
import screen.Screen;

import java.time.LocalDate;

public class Cliente extends Pessoa {
    private ContaCorrente contaCorrente;

    private ContaPoupanca contaPoupanca;

    public Cliente(String nome, String cpf, LocalDate dataNascimento) {
        super(nome, cpf, dataNascimento);
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf(){ return cpf; }

    public Conta getConta(){
        return contaCorrente;
    }

    protected void criarConta(ContaCorrente contaCorrente){
        if (this.contaCorrente == null){
            this.contaCorrente = contaCorrente;
        }else{
            Screen.showMessage("Conta já cadastrada.");
        }
    }
}
