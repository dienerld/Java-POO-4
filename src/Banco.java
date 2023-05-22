import conta.Conta;
import conta.TipoConta;
import pessoa.Cargo;
import pessoa.Cliente;
import pessoa.Gerente;
import screen.Screen;

import java.time.LocalDate;
import java.util.ArrayList;

public class Banco {

    private ArrayList<Cliente> clientes;
    private ArrayList<Gerente> gerentes;

    public Banco() {
        clientes = new ArrayList<>();
        gerentes = new ArrayList<>();

        Gerente gerente1 = new Gerente("Diener", "1234", LocalDate.of(2000,1,1), Cargo.GERENTE_CORRENTE);
        Gerente gerente2 = new Gerente("Pedro", "1234", LocalDate.of(2001,1,1), Cargo.GERENTE_POUPANCA);
        Cliente cliente1 = new Cliente("Maria", "m1", LocalDate.of(2001,1,1));

        clientes.add(cliente1);
        gerente1.criarConta(cliente1, TipoConta.CORRENTE);
        gerentes.add(gerente1);
        gerentes.add(gerente2);
    }


    public void abrirConta(Cliente cliente, TipoConta tipo) {
        var gerente = selecionaGerente(tipo.equals(TipoConta.CORRENTE) ? Cargo.GERENTE_CORRENTE : Cargo.GERENTE_POUPANCA);
        gerente.criarConta(cliente, tipo);
        clientes.add(cliente);
        System.out.println(cliente.getCpf());
    }


    public void verificaSaldo(String cpf) {
        var cliente = buscarCliente(cpf);
        if (cliente != null) {
                Screen.showMessage("Saldo: R$" + cliente.getConta().getSaldo());
            }else {
                Screen.showMessage("CPF invalido!");
            }
    }

    public void depositar(TipoConta tipo, String cpf, double valor) {
        var cliente = buscarCliente(cpf);
        if (cliente != null) {
                cliente.depositar(tipo, valor);
            }
    }

    public void sacar(TipoConta tipo, String cpf, double valor) {
           var cliente = buscarCliente(cpf);
            if (cliente != null) {
                cliente.sacar(tipo, valor);
            }
    }

    public void fazerEmprestimo(String cpf, String nomeGerente, double valor){
       var gerenteConta = buscarGerente(nomeGerente);
       if(gerenteConta == null) {
           Screen.showMessage("Não existe este gerente.");
           return;
       }
       var cliente = buscarCliente(cpf);
        if(cliente == null) {
            Screen.showMessage("Não existe este cliente.");
            return;
        }
       var numConta = cliente.getConta().getNumConta();
        if(numConta == null) {
            Screen.showMessage("Não existe o número da conta informado.");
            return;
        }
       gerenteConta.fazerEmprestimo(numConta, valor);
    }

    public void addConta(String cpf, TipoConta tipo) {
        var cliente = buscarCliente(cpf);
        if(cliente == null) {
            Screen.showMessage("Não existe este cliente.");
            return;
        }
        var gerente = selecionaGerente(tipo.equals(TipoConta.CORRENTE)? Cargo.GERENTE_CORRENTE : Cargo.GERENTE_POUPANCA);
        gerente.criarConta(cliente, tipo);

    }

    private Cliente buscarCliente(String cpf){
        for(Cliente cliente : clientes) {
            if(cliente.getCpf().equalsIgnoreCase(cpf)){
                return cliente;
            }
        }
        return null;
    }
    private Gerente buscarGerente(String nome){
        for(Gerente g : gerentes) {
            if(g.getNome().equalsIgnoreCase(nome)){
                return g;
            }
        }
        return null;
    }

    private Gerente selecionaGerente(Cargo tipoConta) {
        var gerentesFiltrado = gerentes.stream()
                .filter((Gerente g) -> g.getCargo().equals(tipoConta))
                .toList();

        int index = (int) (Math.random() * gerentesFiltrado.size());

        return gerentesFiltrado.get(index);

    }


}
