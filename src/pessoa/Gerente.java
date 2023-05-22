package pessoa;

import conta.*;
import screen.Screen;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gerente extends Pessoa{

    private ArrayList<Conta> contas;

    private Cargo cargo;

    public Gerente(String nome, String cpf, LocalDate dataNascimento, Cargo cargo) {
        super(nome, cpf, dataNascimento);
        this.cargo = cargo;
        contas = new ArrayList<>();
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void criarConta(Cliente cliente, TipoConta tipo) {
        Conta conta;
        if(tipo.equals(TipoConta.CORRENTE)) {
            conta = new ContaCorrente();
            cliente.salvarConta((ContaCorrente)conta);
        } else {
            conta = new ContaPoupanca();
            cliente.salvarConta((ContaPoupanca)conta);
        }
        contas.add(conta);

        System.out.println(conta);
        System.out.println(this.nome);
        Screen.showMessage("Conta criada com sucesso!! \n"+ conta.toString());
    }

    public void fazerEmprestimo(String numConta, double valor){
        for(Conta c : contas){
            if(c.getNumConta().equals(numConta)){
              var contaComEmprestimo = (Emprestimo)c;
              contaComEmprestimo.addEmprestimo(valor);
              return;
            }
        }
        Screen.showMessage("O número da conta informado não é administrado por este gerente.");

    }


}
