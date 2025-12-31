package com.melvin.financeiro.model;

public class Conta {
  // Atributos privados (Encapsulamento)
  private String titular;
  private double saldo;

  // Construtor
  public Conta(String titular, double saldoInicial) {
    this.titular = titular;
    this.saldo = saldoInicial;
  }

  // Métodos de Negócio
  public void depositar(double valor) {
    if (valor > 0) {
      this.saldo += valor;
    }
  }

  // Getters
  public double getSaldo() {
    return this.saldo;
  }

  public String getTitular() {
    return this.titular;
  }
}
