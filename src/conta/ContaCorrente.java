package conta;

import screen.Screen;

public class ContaCorrente extends Conta {

    private double limiteEmprestimo;
    private double chequeEspecial;
    private double taxa = 2.50;

    public ContaCorrente() {
        limiteEmprestimo = 5000.00;
        chequeEspecial = 500;
    }

    @Override
    public String toString() {
        return String.format("Conta corrente\nNumero: %s\nAgência: %s\nSaldo: %.2f", this.numConta, this.agencia, this.saldo);
    }

    @Override
    public void sacar(double valor) {
        if (valor >= 10) {
            saldo -= valor;
            saldo -= taxa;
            Screen.showMessage("Valor sacado: R$" + valor + "\nSaldo em conta: R$" + saldo);
        } else {
            Screen.showMessage("Valor mínimo de saque é R$ 10 reais.");

        }


    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        Screen.showMessage("Valor sacado: R$" + valor + "\nSaldo em conta: R$" + saldo);

    }


}
