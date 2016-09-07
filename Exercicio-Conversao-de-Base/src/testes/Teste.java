package testes;

import conversor.ConversorBase;
import conversor.ConversorBaseBigDecimal;

public class Teste {
	public static void main(String[] args) {
		String numero;

		int baseOrigem = 7;
		int baseDestino = 15;

		// numero inteiro de tamanho limitado
		numero = "12512124";
		System.out.println(numero + " ----- " + ConversorBase.converter(numero, baseOrigem, baseDestino));

		// numero inteiro de tamanho ilimitado
			numero = "1236512124353516251552345145611412343641023400231164";
		System.out.println(numero + " ----- " + ConversorBaseBigDecimal.converter(numero, baseOrigem, baseDestino));

		// numero fracionario de tamanho ilimitado
		numero = "1512,112356";
		System.out.println(
				numero + " ----- " + ConversorBaseBigDecimal.converterFracionario(numero, baseOrigem, baseDestino, 50));
	}
}
