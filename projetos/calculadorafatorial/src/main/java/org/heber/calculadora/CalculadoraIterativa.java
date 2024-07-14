package org.heber.calculadora;

import java.util.Scanner;

public class CalculadoraIterativa {

	private static Scanner scanner;

	public static void main(String[] args) {
		new CalculadoraIterativa().run();
	}

	public CalculadoraIterativa() {
		scanner = new Scanner(System.in);
	}

	private void run() {
		System.out.print("Digite um número: \n> ");
		long numero = scanner.nextLong();
		scanner.nextLine();
		
		long resultado = calcularFatorial(numero);
		System.out.printf("%d! = %d%n", numero, resultado);
	}

	private long calcularFatorial(long numero) {
		long resultado = 1;
		for (long i = 1; i <= numero; i++) {
			resultado *= i;
		}

		return resultado;
	}

}

