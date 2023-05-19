package pessoa;

import conta.Conta;
import conta.ContaCorrente;
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

    public void criarConta(Cliente cliente, Cargo tipoConta) {
        var conta = new ContaCorrente();
        contas.add(conta);
        cliente.salvarConta(conta);
        System.out.println(conta);
        Screen.showMessage("Conta criada com sucesso!! \n"+ conta.toString());
    }

    public void fazerEmprestimo(String numConta, double valor){
        for(Conta c : contas){
            if(c.getNumConta().equals(numConta)){
              var cc = (ContaCorrente)c;
              cc.addEmprestimo(valor);
            }
        }

    }


}
