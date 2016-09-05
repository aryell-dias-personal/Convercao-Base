package testes;

import conversor.ConversorBaseBigDecimal;

public class Teste {
	public static void main(String[] args) {
		String numero = "1.1";
		int bi = 10;
		int bf = 2;
		System.out.println(ConversorBaseBigDecimal.converterFracionario(numero, bi, bf, 10));
	}
}
