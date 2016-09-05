package conversor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

public class ConversorBaseBigDecimal {
	private static final String digitos = "0123456789ABCDEF";

	public static String converter(String numero, int baseOrigem, int baseDestino) {

		numero = numero.toUpperCase();

		if (baseOrigem >= 2 && baseOrigem <= 16 && baseDestino >= 2 && baseDestino <= 16) {

			if (verificarBase(numero, baseOrigem)) {

				if (baseDestino == baseOrigem) {
					return numero;
				} else if (baseOrigem == 10) {
					return deDecimal(new BigDecimal(numero), baseDestino);
				} else if (baseDestino == 10) {
					return paraDecimal(numero, baseOrigem).toString();
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

	public static String converterFracionario(String numero, int baseOrigem, int baseDestino, int limite) {
		numero = numero.toUpperCase();
		String[] aux;

		if (numero.contains(",")) {
			aux = numero.split(",");
		} else {
			return converter(numero, baseOrigem, baseDestino);
		}
		return converter(aux[0], baseOrigem, baseDestino) + ","
				+ deDecimalFracionario(paraDecimalFracionario(aux[1], baseOrigem, limite), baseDestino,limite);
	}

	private static BigDecimal paraDecimalFracionario(String numero, int baseOrigem, int limite) {
		BigDecimal decimal = BigDecimal.ZERO;
		long numeroDigitos = numero.length();
		BigDecimal bigBase = BigDecimal.valueOf(baseOrigem);
		MathContext context = new MathContext(limite, RoundingMode.DOWN);
		for (int i = 0; i < numeroDigitos; i++) {
			BigDecimal valorDigito = BigDecimal.valueOf(digitos.indexOf(numero.charAt(i)));
			decimal = decimal.add(valorDigito.divide(bigBase.pow((int) (i + 1)), context));
		}
		return decimal;
	}

	private static String deDecimalFracionario(BigDecimal decimal, int baseDestino,int limite) {
		
		if(decimal.equals(BigDecimal.ZERO)){
			return "";
		}else{
			for(int i= 0; i<limite; i++){
				decimal = decimal.multiply(BigDecimal.TEN);
			}
		}
		
		if (decimal.compareTo(BigDecimal.valueOf(baseDestino)) < 0) {
		
			return Character.toString(digitos.charAt(decimal.intValue()));
		
		} else {
			
			String retorno = "";
			String proximoDecimal = "";
			String sD = decimal.toString();
			String sM = (decimal.multiply(BigDecimal.valueOf(baseDestino)).toString());
			if (sD.length() < sM.length()) {
				
				retorno = sM.substring(0,sM.length() - sD.length());
				proximoDecimal = sM.substring(retorno.length(), sM.length());
				
				if(limite>0){
					return Character.toString(digitos.charAt(Integer.parseInt(retorno))) + 
							deDecimalFracionario(new BigDecimal(proximoDecimal), baseDestino, limite-1);
				}else{
					return Character.toString(digitos.charAt(Integer.parseInt(retorno)));
				}
			}else{	
				
				if(limite >0){
					return "0" + deDecimalFracionario(new BigDecimal(sM), baseDestino, limite-1);
				}else{
					return "";
				}
				
			}
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
	private static BigDecimal paraDecimal(String numero, int base) {

		BigDecimal decimal = BigDecimal.ZERO;
		long numeroDigitos = numero.length();
		BigDecimal bigBase = BigDecimal.valueOf(base);

		for (int i = 0; i < numeroDigitos; i++) {
			BigDecimal valorDigito = BigDecimal.valueOf(digitos.indexOf(numero.charAt(i)));
			decimal = decimal.add(valorDigito.multiply(bigBase.pow((int) (numeroDigitos - 1 - i))));
		}

		return decimal;
	}

	// parte recursiva
	private static String deDecimal(BigDecimal decimal, int baseDestino) {

		if (decimal.compareTo(BigDecimal.valueOf(baseDestino)) < 0) {
			return Character.toString(digitos.charAt(decimal.intValue()));
		} else {
			return deDecimal(decimal.divide(BigDecimal.valueOf(baseDestino), BigDecimal.ROUND_DOWN), baseDestino)
					+ digitos.charAt((decimal.remainder(BigDecimal.valueOf(baseDestino)).intValue())) + "";
		}
	}
	
}
