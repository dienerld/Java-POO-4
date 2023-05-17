package conta;

public abstract class Conta {

    protected String numConta;
    protected String agencia;
    protected double saldo;

    public Conta() {
        numConta = generateNumber();
        agencia = generateAgency();
        saldo = 0;
    }

    private String generateNumber() {
        // gera numero para uma conta
        var number = (int) (Math.random() * 1000000);
        return String.valueOf(number);
    }

    private String generateAgency() {
//        var number = Math.random() * 10000;
//        var digit = Math.random() * 100;
//        var agency = String.valueOf(number);
//        var suffix = String.valueOf(digit);
//
//        return agency.concat("-").concat(suffix);

        return "1234-01";
    }

    public abstract void sacar(double valor);

    public abstract void depositar(double valor);

    public double getSaldo(){return saldo;}
}
