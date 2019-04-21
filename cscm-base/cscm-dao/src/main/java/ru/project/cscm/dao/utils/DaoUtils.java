package ru.project.cscm.dao.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import org.springframework.util.StringUtils;

public final class DaoUtils {

	private DaoUtils() {
		super();
	}

	public static <T extends Number> String generateInConditionNumber(
			final String columnName, final Collection<T> list) {
		final StringBuilder sb = new StringBuilder(columnName);
		sb.append(" IN ").append("(");
		sb.append(StringUtils.collectionToCommaDelimitedString(list));
		sb.append(")");

		return sb.toString();
	}

	public static String getOperationType(String value) {
		return ((value != null && findLikeSymbol(value)) ? " LIKE " : " = ");
	}

	public static boolean findLikeSymbol(String s) {
		int percent = s.indexOf('%');
		int space = s.indexOf('_');

		return ((percent > -1) || (space > -1));
	}
	
	public static Date getDate(java.sql.Date date) {
		return (date == null) ? null : new Date(date.getTime());
	}

	public static Date getTime(Time time) {
		return (time == null) ? null : new Date(time.getTime());
	}

	public static Date getTimestamp(Timestamp timestamp) {
		return (timestamp == null) ? null : new Date(timestamp.getTime());
	}

	public static java.sql.Date getSqlDate(Date date) {
		return (date == null) ? null : new java.sql.Date(date.getTime());
	}

	public static java.sql.Time getSqlTime(Date time) {
		return (time == null) ? null : new java.sql.Time(time.getTime());
	}

	public static java.sql.Timestamp getSqlTimestamp(Date timestamp) {
		return (timestamp == null) ? null : new Timestamp(timestamp.getTime());
	}
}
