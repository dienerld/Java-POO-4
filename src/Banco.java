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
        Gerente gerente2 = new Gerente("Pedro", "1234", LocalDate.of(2001,1,1), Cargo.GERENTE_CORRENTE);
        gerentes.add(gerente1);
        gerentes.add(gerente2);
    }



    public void abrirConta(Cliente cliente, Cargo tipoConta) {
        var gerente = selecionaGerente(tipoConta);
        gerente.abrirConta(cliente, tipoConta);
        clientes.add(cliente);
        System.out.println(cliente.getCpf());
    }

    private Gerente selecionaGerente(Cargo tipoConta) {
        var gerentesFiltrado = gerentes.stream()
                .filter((Gerente g) -> g.getCargo().equals(tipoConta))
                .toList();

        int index = (int) (Math.random() * gerentesFiltrado.size());

        return gerentesFiltrado.get(index);

    }


    public void verificaSaldo(String cpf) {
        for (Cliente cliente: clientes) {
            if (cliente.getCpf().equals(cpf)){
                Screen.showMessage("Saldo: R$" + cliente.getConta().getSaldo());
            }else {
                Screen.showMessage("CPF invalido!");
            }
        }
    }
}
