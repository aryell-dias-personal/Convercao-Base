package testes;

import conversor.ConversorBaseBigDecimal;

public class Teste {
	public static void main(String[] args) {
		String numero = "1010,11";
		int bi = 2;
		int bf = 10;
		System.out.println(ConversorBaseBigDecimal.converterFracionario(numero, bi, bf,10));
	}
}
