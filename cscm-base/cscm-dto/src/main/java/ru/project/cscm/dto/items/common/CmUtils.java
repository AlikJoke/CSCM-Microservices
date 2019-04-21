package ru.project.cscm.dto.items.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;

import ru.project.cscm.dto.items.core.IdentifiableObject;

public final class CmUtils {

	private CmUtils() {
		super();
	}

	public static String getIdentifiableListInClause(
			List<? extends IdentifiableObject<?>> filterList, String keyWord) {
		if (filterList.isEmpty()) {
			return new String(" AND  1 != 1 ");
		} else {
			StringBuffer res = new StringBuffer(" AND  " + keyWord + " IN (");
			for (IdentifiableObject<?> i : filterList) {
				res.append(String.valueOf(i.getId()));
				res.append(",");
			}
			res.deleteCharAt(res.length() - 1);
			res.append(") ");
			return res.toString();
		}

	}

	public static String getInstListInClause(List<Institute> instList,
			String keyWord) {
		if (instList.isEmpty()) {
			return new String(" AND  1 != 1 ");
		} else {
			StringBuffer res = new StringBuffer(" AND  " + keyWord + " IN (");
			for (Institute i : instList) {
				res.append("'").append(i.getId()).append("'");
				res.append(",");
			}
			res.deleteCharAt(res.length() - 1);
			res.append(") ");
			return res.toString();
		}

	}

	public static String getIdListInClause(List<Integer> idList, String keyWord) {
		if (idList.isEmpty()) {
			return new String(" AND  1 != 1 ");
		} else {
			StringBuffer res = new StringBuffer(" AND  " + keyWord + " IN (");
			for (Integer i : idList) {
				res.append(String.valueOf(i));
				res.append(",");
			}
			res.deleteCharAt(res.length() - 1);
			res.append(") ");
			return res.toString();
		}

	}

	public static String getStringListCommaSeparated(List<String> stringList) {
		if (stringList != null && stringList.size() > 0) {
			StringBuffer res = new StringBuffer();
			for (String i : stringList) {
				res.append(i);
				res.append(",");
			}
			res.deleteCharAt(res.length() - 1);
			return res.toString();
		} else {
			return new String();
		}

	}

	public static List<Integer> getIdListFromString(String source) {
		List<Integer> res = new ArrayList<Integer>();
		if (StringUtils.hasLength(source)) {
			for (String i : source.split(",")) {
				res.add(Integer.valueOf(i.trim()));
			}
		}
		return res;
	}

	public static String getInstDescxFormListById(List<Institute> instList,
			String instId) {
		for (Institute i : instList) {
			if (i.getId().equals(instId)) {
				return i.getDescription();
			}
		}
		return "";
	}

	public static int getHoursBetweenTwoDates(Calendar start, Calendar finish) {
		return Math.abs(getHrsBetweenTwoDates(start, finish));
	}

	public static int getHoursBetweenTwoDates(Date startDate, Date finishDate) {
		return Math.abs(getHrsBetweenTwoDates(startDate, finishDate));
	}

	private static int getHrsBetweenTwoDates(Calendar start, Calendar finish) {
		return getDaysBetweenTwoDates(start, finish) * 24
				- start.get(Calendar.HOUR_OF_DAY)
				+ finish.get(Calendar.HOUR_OF_DAY);
	}

	private static int getHrsBetweenTwoDates(Date startDate, Date finishDate) {
		Calendar start = Calendar.getInstance();
		Calendar finish = Calendar.getInstance();
		start.setTime(startDate);
		finish.setTime(finishDate);
		return getDaysBetweenTwoDates(start, finish) * 24
				- start.get(Calendar.HOUR_OF_DAY)
				+ finish.get(Calendar.HOUR_OF_DAY);
	}

	public static List<Date> getHourlyDatesBetweenTwoDates(Date startDate,
			Date finishDate) {
		List<Date> dates = new ArrayList<Date>();

		Calendar start = Calendar.getInstance();
		Calendar finish = Calendar.getInstance();
		start.setTime(startDate);
		finish.setTime(finishDate);

		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);

		while (start.before(finish)) {
			dates.add(start.getTime());
			start.add(Calendar.HOUR_OF_DAY, 1);
		}

		return dates;
	}

	public static int getDaysBetweenTwoDates(Date startDate, Date finishDate) {
		Calendar start = Calendar.getInstance();
		Calendar finish = Calendar.getInstance();
		start.setTime(startDate);
		finish.setTime(finishDate);
		return getDaysBetweenTwoDates(start, finish);
	}

	public static int getDaysBetweenTwoDates(Calendar start, Calendar finish) {
		int yearsDiff = finish.get(Calendar.YEAR) - start.get(Calendar.YEAR);
		if (yearsDiff == 0) {
			return finish.get(Calendar.DAY_OF_YEAR)
					- start.get(Calendar.DAY_OF_YEAR);
		} else {
			int daysBetween = finish.get(Calendar.DAY_OF_YEAR);
			daysBetween += start.getActualMaximum(Calendar.DAY_OF_YEAR)
					- start.get(Calendar.DAY_OF_YEAR);
			for (int i = 1; i < yearsDiff; i++) {
				start.add(Calendar.YEAR, 1);
				daysBetween += start.getActualMaximum(Calendar.DAY_OF_YEAR);
			}
			return daysBetween;
		}
	}

	public static Date truncateDate(Date srcDate) {
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(srcDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Calendar truncateCalendar(Calendar cal) {
		cal.setTime(truncateDate(cal.getTime()));
		return cal;
	}

	public static Date truncateDateToHours(Date srcDate) {
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(srcDate);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static ObjectPair<Date, Date> getDatesForFilter(int daysBeforeToday,
			int daysFilterInterval) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -daysBeforeToday);
		Date startDate = truncateDate(cal.getTime());

		cal.add(Calendar.DAY_OF_YEAR, daysFilterInterval);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new ObjectPair<Date, Date>(startDate, cal.getTime());
	}

	public static <T> T getNVLValue(T obj, T defValue) {
		return obj == null ? defValue : obj;
	}

	public static <T extends Comparable<T>> T getMinValue(T obj1, T obj2) {
		return obj1.compareTo(obj2) <= 0 ? obj1 : obj2;
	}

	public static <T extends Comparable<T>> T getMaxValue(T obj1, T obj2) {
		return obj1.compareTo(obj2) >= 0 ? obj1 : obj2;
	}

	public static <T extends Enum<T> & IdentifiableObject<Integer>> T getEnumValueById(
			Class<T> enumClass, int id) {
		T result = null;
		for (Enum<T> element : enumClass.getEnumConstants()) {
			@SuppressWarnings("unchecked")
			T value = (T) element;
			if (value.getId() == id) {
				result = value;
				break;
			}
		}
		return result;
	}

	public static <T extends IdentifiableObject<Integer>> int compareIdentifiables(
			T obj1, T obj2) {
		return obj1 == null ? (obj2 == null ? 0 : -1) : (obj2 == null ? 1
				: Integer.valueOf(obj1.getId()).compareTo(obj2.getId()));
	}

	public static Set<Integer> getIdsSet(
			Collection<? extends IdentifiableObject<Integer>> identifiables) {
		Set<Integer> ids = new HashSet<Integer>(identifiables.size());
		for (IdentifiableObject<Integer> identifiable : identifiables) {
			ids.add(identifiable.getId());
		}
		return ids;
	}

	public static List<Integer> getIdsList(String idList, String delimiter) {
		String[] stringArray = idList.split(delimiter);
		List<Integer> ids = new ArrayList<Integer>(stringArray.length);
		for (String id : stringArray) {
			ids.add(Integer.parseInt(id));
		}
		return ids;
	}

	public static String getAtmFullAdrress(String state, String city,
			String street) {
		StringBuilder sb = new StringBuilder();
		if (state != null) {
			sb.append(state);
			sb.append(',');
		}
		if (city != null) {
			sb.append(city);
			sb.append(',');
		}
		if (street != null) {
			sb.append(street);
		}
		return sb.toString();
	}

	public static String getAtmNameLabel(String atmId, String atmName) {
		StringBuilder sb = new StringBuilder(atmId);
		if (atmName != null) {
			sb.append(" (");
			sb.append(atmName);
			sb.append(')');
		}
		return sb.toString();
	}

}
