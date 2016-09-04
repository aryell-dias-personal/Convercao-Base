package conversor;

import java.security.InvalidParameterException;

public class ConversorBase {

	private static final String digitos = "0123456789ABCDEF";

	public static String converter(String numero, long baseOrigem, long baseDestino) {

		numero = numero.toUpperCase();

		if (baseOrigem >= 2 && baseOrigem <= 16 && baseDestino >= 2 && baseDestino <= 16) {

			if (verificarBase(numero, baseOrigem)) {

				if (baseDestino == baseOrigem) {
					return numero;
				} else if (baseOrigem == 10) {
					return deDecimal(Long.parseLong(numero), baseDestino);
				} else if (baseDestino == 10) {
					return Long.toString(paraDecimal(numero, baseOrigem));
				} else {
					// baseOrigem --> base10 --> baseDestino
					return deDecimal(paraDecimal(numero, baseOrigem), baseDestino);
				}

			} else {
				throw new InvalidParameterException("O numero não está na base informada");
			}
		} else {
			throw new InvalidParameterException("Base invalida");
		}
	}

	private static boolean verificarBase(String numero, long base) {
		for (int i = 0; i < numero.length(); i++) {
			int valor = digitos.indexOf(numero.charAt(i));
			if (valor >= base || valor == -1) {
				return false;
			}
		}
		return true;
	}

	// parte iterativa
	private static long paraDecimal(String numero, long base) {

		long decimal = 0;
		long numeroDigitos = numero.length();

		for (int i = 0; i < numeroDigitos; i++) {
			long valorDigito = digitos.indexOf(numero.charAt(i));
			decimal += valorDigito * Math.pow(base, numeroDigitos - 1 - i);
		}

		return decimal;
	}

	// parte recursiva
	private static String deDecimal(long decimal, long baseDestino) {
		if (decimal < baseDestino) {
			return Character.toString(digitos.charAt((int) decimal));
		} else {
			return deDecimal(decimal / baseDestino, baseDestino) + digitos.charAt((int) (decimal % baseDestino));
		}
	}
}
