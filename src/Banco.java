import conta.TipoConta;
import exceptions.NotFoundUserException;
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

        Gerente gerente1 = new Gerente("Diener", "1234", LocalDate.of(2000, 1, 1), Cargo.GERENTE_CORRENTE);
        Gerente gerente2 = new Gerente("Pedro", "1234", LocalDate.of(2001, 1, 1), Cargo.GERENTE_POUPANCA);
        Cliente cliente1 = new Cliente("Maria", "m1", LocalDate.of(2001, 1, 1));

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


    public void verificaSaldo(String cpf, TipoConta conta) {
        var cliente = buscarCliente(cpf);


        var contaCliente = cliente.getConta(conta);
        if (contaCliente != null)
            Screen.showMessage("Saldo: R$" + contaCliente.getSaldo());
        else
            Screen.showMessage("Conta não existe!");
    }


    public void depositar(TipoConta tipo, String cpf, double valor) {
        var cliente = buscarCliente(cpf);

        cliente.depositar(tipo, valor);

    }

    public void sacar(TipoConta tipo, String cpf, double valor) {
        var cliente = buscarCliente(cpf);
        try {
            cliente.sacar(tipo, valor);
        } catch (NullPointerException e) {
            Screen.showMessage("Não existe esta conta");
            var opcao = Screen.getInput("Informe qual tipo de conta(1 - Corrente | 2 - Poupança): ");
            var novoTipo = opcao.equals("1") ? TipoConta.CORRENTE : TipoConta.POUPANCA;
            cliente.sacar(novoTipo, valor);
        }
    }

    public void fazerEmprestimo(String cpf, String nomeGerente, double valor) {
        var gerenteConta = buscarGerente(nomeGerente);

        var cliente = buscarCliente(cpf);
        Screen.showMessage("Não existe este cliente.");

        var numConta = cliente.getConta(TipoConta.CORRENTE).getNumConta();
        if (numConta == null) {
            Screen.showMessage("Não existe o número da conta informado.");
            return;
        }
        gerenteConta.fazerEmprestimo(numConta, valor);
    }

    public void addConta(String cpf, TipoConta tipo) {
        var cliente = buscarCliente(cpf);
        Screen.showMessage("Não existe este cliente.");

        var gerente = selecionaGerente(tipo.equals(TipoConta.CORRENTE) ? Cargo.GERENTE_CORRENTE : Cargo.GERENTE_POUPANCA);
        gerente.criarConta(cliente, tipo);

    }

    private Cliente buscarCliente(String cpf) throws NotFoundUserException {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equalsIgnoreCase(cpf)) {
                return cliente;
            }
        }
        throw new NotFoundUserException("Cliente");
    }

    private Gerente buscarGerente(String nome) {
        for (Gerente g : gerentes) {
            if (g.getNome().equalsIgnoreCase(nome)) {
                return g;
            }
        }
        throw new NotFoundUserException("Gerente");
    }

    private Gerente selecionaGerente(Cargo tipoConta) {
        var gerentesFiltrado = gerentes.stream()
                .filter((Gerente g) -> g.getCargo().equals(tipoConta))
                .toList();

        int index = (int) (Math.random() * gerentesFiltrado.size());

        return gerentesFiltrado.get(index);
    }


}
