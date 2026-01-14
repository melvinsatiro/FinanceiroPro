package com.melvin.financeiro;

import com.melvin.financeiro.model.Conta;

public class Principal {

  public static void main(String[] args) {

    Conta c1 = new Conta("Melvin", "444.333.222-11", 1000.0);

    double saldoInicial = c1.getSaldo();

    c1.depositar(500.0);

    double saldoFinal = c1.getSaldo();

    if (saldoFinal == (saldoInicial + 500.0)) {
      System.out.println("---Saldo atual R$ " + saldoFinal + "---");

    } else {

      System.out.println("Saldo errado ");

    }

  }
}
