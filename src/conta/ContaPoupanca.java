package conta;

import screen.Screen;

public class ContaPoupanca  extends Conta{

    private double juros = 0.1;


    @Override
    public void sacar(double valor) {
        if(valor >= 10){
            saldo -= valor;
            Screen.showMessage("Valor sacado: R$" + valor + "\nSaldo em conta: R$" + saldo);
        }else{
            Screen.showMessage("Valor mínimo de saque é R$ 10 reais.");

        }


    }

    @Override
    public void depositar(double valor) {
        var adicionalJuros = valor * juros;
        saldo += valor;
        saldo += adicionalJuros;
        Screen.showMessage("Valor sacado: R$" + valor + "\nSaldo em conta: R$" + saldo);
    }
}
