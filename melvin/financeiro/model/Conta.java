package com.melvin.financeiro.model;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Conta {
  private String titular;
  private String cpf;
  private double saldo;

  public Conta(String titular, String cpf, double saldoInicial) {
    this.titular = titular;
    this.saldo = saldoInicial;

    // --- AUTOMAÇÃO DA FORMATAÇÃO ---
    // Chamamos o método estático para tratar o CPF recebido antes de salvá-lo.
    // Assim, se o usuário passar "12345678901", o sistema salva "123.456.789-01".
    String resultadoFormatacao = formatarCpf(cpf);

    if (resultadoFormatacao.equals("Número de CPF inválido!")) {
      System.out.println("Aviso: O CPF '" + cpf + "' é inválido e será salvo sem formatação.");
      this.cpf = cpf;
    } else {
      this.cpf = resultadoFormatacao; // Salva o CPF bonitinho!
    }
  }

  /**
   * Método que limpa e formata o CPF.
   * Agora ele é mais robusto: aceita CPF com pontos ou apenas números.
   */
  public static String formatarCpf(String cpfOriginal) {
    // 1. Remove tudo que NÃO for número (ex: remove pontos e hífens antigos)
    // Isso evita que o Regex falhe se o usuário digitar "123.456..."
    String apenasNumeros = cpfOriginal.replaceAll("\\D", "");

    // 2. Define o padrão de 11 dígitos divididos em 4 grupos de captura
    String regex = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(apenasNumeros);

    // 3. Se houver 11 números, aplica a máscara padrão brasileira
    if (matcher.matches()) {
      // Usamos os grupos de captura para montar a String formatada
      return String.format("%s.%s.%s-%s",
          matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
    }

    return "Número de CPF inválido!";
  }

  public void depositar(double valor) {
    if (valor > 0) {
      this.saldo += valor;
    }
  }

  public double getSaldo() {
    return this.saldo;
  }

  public String getTitular() {
    return this.titular;
  }

  public String getCpf() {
    return this.cpf;
  }
}
