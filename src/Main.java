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
                    """)) {
                case "1" -> managerBank();
                case "2" -> managerClient();
                default -> System.exit(0);
            }
        }
    }
    public static void managerBank() {
        Screen.showMessage("Gerenciando bank");

    }

    public static void managerClient() {
        while (true){
            switch (Screen.getInput("""
                    1 - Abrir conta
                    2 - Verificar Saldo
                    3 - Sacar
                    4 - Depositar
                    
                    """)){
                case "1" -> {
                    var nome =  Screen.getInput("Nome: ");
                    var cpf =  Screen.getInput("cpf: ");
                    var cliente = new Cliente(nome,cpf, LocalDate.of(1000,1,1));
                    banco.abrirConta(cliente, Cargo.GERENTE_CORRENTE);
                }
                case "2" ->{
                    var cpf = Screen.getInput("Informe seu cpf: ");
                    banco.verificaSaldo(cpf);
                }
                case "4" ->{
                    var tipoConta = buscarTipoConta();
                    var cpf = Screen.getInput("Informe seu cpf: ");
                    var valor = Double.parseDouble(Screen.getInput("Informe o valor a depositar: "));
                    banco.depositar(tipoConta, cpf, valor);

                }


                default -> System.exit(0);

            }

        }

    }
    public static TipoConta buscarTipoConta() {
        var type = Screen.getInput("Informe qual tipo de conta(1 - Corrente | 2 - Poupança): ");
        return type.equals("1") ? TipoConta.CORRENTE : TipoConta.POUPANCA;
    }
}