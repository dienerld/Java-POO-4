package conta;

import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;
import screen.Screen;

public class ContaCorrente extends Conta implements Emprestimo {

    private double limiteEmprestimo;
    private double chequeEspecial;
    private double limiteChequeEspecial;
    private double taxa = 2.50;
    private double jurosChequeEsp = 0.2;

    public ContaCorrente() {
        limiteEmprestimo = 5000.00;
        chequeEspecial = limiteChequeEspecial = 500;
    }

    @Override
    public String toString() {
        return String.format("Conta corrente\nNumero: %s\nAgência: %s\nSaldo: %.2f", this.numConta, this.agencia, this.saldo);
    }

    @Override
    public void sacar(double valor) {

        if (valor < 10) {
            throw new ValorInvalidoException("Valor do saque deve ser maior que 10 reais");
        }
        if (valor > saldo + chequeEspecial) {
            throw new SaldoInsuficienteException("Valor do saque é maior que saldo e limite de chegue especial");
        }

        saldo -= taxa;
        if (valor > saldo) {
            var valorCheque = valor - saldo;
            saldo = 0;
            chequeEspecial -= valorCheque * jurosChequeEsp;
            chequeEspecial -= valorCheque;
            Screen.showMessage("Valor sacado: R$" + valor + "\nSaldo em cheque especial: R$" + chequeEspecial);
            return;
        }
        saldo -= valor;
        Screen.showMessage("Valor sacado: R$" + valor + "\nSaldo em conta: R$" + saldo);

    }

    @Override
    public void depositar(double valor) {
        var valorDepositado = valor;
        if (chequeEspecial < limiteChequeEspecial) {
            var valorGasto = limiteChequeEspecial - chequeEspecial;
            if (valor <= valorGasto) {
                chequeEspecial += valor;
                Screen.showMessage("Valor depositado R$ " + valor + "\nSaldo cheque especial: R$ " + chequeEspecial);
                return;
            } else {
                valor -= valorGasto;
                chequeEspecial += valorGasto;
            }
        }

        saldo += valor;
        Screen.showMessage("Valor depositado: R$" + valorDepositado + "\nSaldo em conta: R$" + saldo);

    }


    @Override
    public void addEmprestimo(double valor) {
        if (valor > limiteEmprestimo) {
            Screen.showMessage("O valor está acima do limite de empréstimo");
        } else {
            limiteEmprestimo -= valor;
            saldo += valor;
            Screen.showMessage("Empréstimo realizado com sucesso.");
        }

    }

    @Override
    public void pagarEmprestimo(double valor) {

    }


}
