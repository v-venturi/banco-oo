package model;

import lombok.Getter;

import java.util.Random;

@Getter
public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1000;
    private static int SEQUENCIAL = 1000;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected int digito;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.digito = getDigito();
    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    protected void gerarDigito() {
        Random geraDigito = new Random();
        digito = geraDigito.nextInt(9);
        System.out.print("-" + digito + "\n");
    }

    protected void imprimirInfosComuns() {
        System.out.printf("Titular: %s%n", this.cliente.getNome());
        System.out.printf("Agencia: %d%n", this.agencia);
        System.out.printf("Numero: %d", this.numero);
        gerarDigito();
        System.out.printf("Saldo: %.2f%n", this.saldo);
        System.out.println("............");
    }
}
