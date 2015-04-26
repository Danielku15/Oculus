package at.itb13.oculus.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	private DateUtil() {}
	
	public static Date truncateHours(Date date) {
		if(date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		return null;
	}
	
	public static String format(Date date) {
		if(date != null) {
			return SDF.format(date);
		}
		return null;
	}
	
	public static Date parse(String dateStr) throws ParseException {
		return SDF.parse(dateStr);
	}
}
