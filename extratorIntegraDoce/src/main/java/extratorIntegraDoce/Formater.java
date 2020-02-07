package extratorIntegraDoce;

import java.text.SimpleDateFormat;

public class Formater {
	
	// https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");
	
	public static boolean isNumber(String str) {
		return str.matches("[+-]*(\\d+)*(\\.\\d+)*");
	}
	
	public static Double toDouble(String str) {
		
		if(Formater.isNumber(str))
			return Double.parseDouble(str);
		
		str.replace(",", ".");
		if(Formater.isNumber(str))
			return Double.parseDouble(str);
		
		return null;
		
	}

}
