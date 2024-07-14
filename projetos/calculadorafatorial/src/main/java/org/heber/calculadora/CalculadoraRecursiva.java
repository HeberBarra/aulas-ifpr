package org.heber.calculadora;

import java.util.Scanner;

public class CalculadoraRecursiva {

	private Scanner scanner;

	public static void main(String[] args) {
		new CalculadoraRecursiva().run();
	}

	public CalculadoraRecursiva() {
		scanner = new Scanner(System.in);
	}

	private void run() {
		System.out.print("Digite um número: \n> ");
		long numero = scanner.nextLong();
		long resultado = calcularFatorial(numero);
		System.out.printf("%d! = %d%n", numero, resultado);
	}

	private long calcularFatorial(long numero) {

		if (numero == 1 || numero == 0) return 1;

		return calcularFatorial(numero - 1) * numero;
	}

}

