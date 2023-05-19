package pessoa;

import conta.Conta;
import conta.ContaCorrente;
import conta.ContaPoupanca;
import conta.TipoConta;
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

    protected void salvarConta(ContaCorrente contaCorrente){
        if (this.contaCorrente == null){
            this.contaCorrente = contaCorrente;
        }else{
            Screen.showMessage("Conta já cadastrada.");
        }
    }

    public void depositar(TipoConta tipoConta, double valor) {
        if(tipoConta.equals(TipoConta.CORRENTE)) {
            contaCorrente.depositar(valor);
        } else {
            contaPoupanca.depositar(valor);
        }
    }
}
