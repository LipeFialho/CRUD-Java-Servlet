package persistence;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class UtilsBanco {
	
	public static GregorianCalendar converterData(String data) {
		String novadata[] = data.split("-");
		String ano = novadata[0];
		String mes = novadata[1];
		String dia = novadata[2];
		
		GregorianCalendar dtgc = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes) -1,Integer.parseInt(dia));
		
		return dtgc;
	}
	
	public static String ConvertDataToDatabase(GregorianCalendar dtgc) {
		String dtFormatada ="";
		
		if(dtgc != null) {
			SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-dd");
			dtFormatada = dt.format(dtgc.getTime());
		}
		return dtFormatada;
	}
}
