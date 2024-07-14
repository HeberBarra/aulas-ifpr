package org.heber.calculadora;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private static final int LIMITE = 300;
	private static final String ARQUIVO = "numeros.txt";
	private List<Integer> numeros_primos;

	public static void main(String[] args) {
		new Main().run();
	}

	public Main() {
		numeros_primos = new ArrayList<>();
	}

	private void run() {
		for (int i = 1; i <= LIMITE; i ++) {
			if (verificarNumeroPrimo(i)) {
				numeros_primos.add(i);
			}
		}	

		salvarNumeros();
	}

	private void salvarNumeros() {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ARQUIVO, StandardCharsets.UTF_8))) {
			bufferedWriter.write("Números primos: \n");

			for (int numero: numeros_primos) {
				bufferedWriter.write("%d%n".formatted(numero));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean verificarNumeroPrimo(int numero) {
		if (numero == 2) {
			return true;
		}

		if (numero % 2 == 0 || numero == 1) {
			return false;
		}

		for (int fator_primo: numeros_primos) {
			if (numero % fator_primo == 0) {
				return false;
			}
		}

		return true;
	}

}

