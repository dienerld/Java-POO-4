import conta.TipoConta;
import pessoa.Cargo;
import pessoa.Cliente;
import screen.Screen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Banco banco = new Banco();

    public static void main(String[] args) {
        while (true) {
            switch (Screen.getInput("""
                    Bem vindo ao GrowBank
                    O que você é?
                                       
                    1 - Gerente
                    2 - Cliente
                    3 - Criar Conta
                    """)) {
                case "1" -> managerBank();
                case "2" -> managerClient();
                case "3" -> addConta();
                default -> System.exit(0);
            }
        }
    }
    public static void managerBank() {
        Screen.showMessage("Gerenciando bank");

    }

    public static void managerClient() {
        var cpf =  pegarCPF();
        while (true){
            switch (Screen.getInput("""
                    1 - Adicionar conta
                    2 - Verificar Saldo
                    3 - Sacar
                    4 - Depositar
                    5 - Pedir Empréstimo
                    
                    """)){
                case "1" -> {
                    var tipoConta = buscarTipoConta();
                    banco.addConta(cpf, tipoConta);
                }
                case "2" ->{
                    banco.verificaSaldo(cpf, buscarTipoConta());
                }
                case "3" -> {
                    var tipoConta = buscarTipoConta();
                    var valor = Double.parseDouble(Screen.getInput("Informe o valor a ser sacado: "));
                    banco.sacar(tipoConta, cpf, valor);

                }
                case "4" ->{
                    var tipoConta = buscarTipoConta();
                    var valor = Double.parseDouble(Screen.getInput("Informe o valor a depositar: "));
                    banco.depositar(tipoConta, cpf, valor);

                }
                case "5" -> {
                    var nomeGerente = Screen.getInput("Informe o nome do Gerente:");
                    var valorEmprestimo = Double.parseDouble(Screen.getInput("Informe o valor do empréstimo:"));
                    banco.fazerEmprestimo(cpf, nomeGerente, valorEmprestimo);

                }

                default -> System.exit(0);

            }

        }

    }


    public static TipoConta buscarTipoConta() {
        var type = Screen.getInput("Informe qual tipo de conta(1 - Corrente | 2 - Poupança): ");
        return type.equals("1") ? TipoConta.CORRENTE : TipoConta.POUPANCA;
    }

    public static void addConta(){
        var nome = Screen.getInput("Informe seu nome:");
        var cpf = pegarCPF();
        var tipoConta = buscarTipoConta();
        banco.abrirConta(new Cliente(nome,cpf,LocalDate.of(1989,2,15)),tipoConta);

    }
    public static String pegarCPF(){
        return Screen.getInput("Informe seu cpf: ");
    }
}