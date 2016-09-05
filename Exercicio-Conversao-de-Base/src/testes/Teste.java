package testes;

import conversor.ConversorBaseBigDecimal;

public class Teste {
	public static void main(String[] args) {
		String numero = "11.1";
		int bi = 16;
		int bf = 2;
		System.out.println(ConversorBaseBigDecimal.converterFracionario(numero, bi, bf, 10));

	}
}
