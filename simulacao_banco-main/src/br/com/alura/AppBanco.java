package br.com.alura;

import java.math.BigDecimal;

public class AppBanco {

    public static void main(String[] args) {
        var cliente = new Cliente("João");
        var conta = new Conta(cliente, new BigDecimal("150"));

        var operacao = new OperacaoSaque(conta, new BigDecimal("150"));

        Thread saqueJoao = new Thread(operacao);
        Thread saqueMaria = new Thread(operacao);

        saqueJoao.start();
        saqueMaria.start();

        try {
            saqueJoao.join();
            saqueMaria.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // O método start() inicia a thread e join() faz com que a execução espere até que essa thread termine para continuar,
        // inicia um imediatamente após o outro.

        System.out.println(Thread.currentThread().getName());
        System.out.println("Saldo final: " + conta.getSaldo());

//        //saque joao
//        operacao.executa();
//
//        //saque maria
//        operacao.executa();
    }
}
