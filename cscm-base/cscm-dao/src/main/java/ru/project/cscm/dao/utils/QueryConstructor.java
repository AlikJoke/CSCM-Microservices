package ru.project.cscm.dao.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class QueryConstructor {
	
	private List<QueryElement> queryElements;
	private String queryBody;
	private String queryTail;
	private List<QueryElement> list;

	private boolean isConditionBlockExists = false;

	@SuppressWarnings("unused")
	private static class QueryElement {
		public String name;
		public String prependWord;
		public Object fieldValue;
		public String comparison;
		public String dbFieldName;

		public boolean simpleExpr = false;
		public boolean simpleExprWithParam = false;
	}

	public QueryConstructor() {
		queryElements = new ArrayList<QueryElement>();
		list = new ArrayList<QueryElement>();
	}

	public void setQueryBody(String body) {
		queryBody = body;
	}

	public void setQueryBody(String body, boolean isConditionBlockExists) {
		queryBody = body;
		this.isConditionBlockExists = isConditionBlockExists;
	}

	public void setQueryTail(String tail) {
		if (queryTail != null && !queryTail.equals(""))
			queryTail += tail;
		else
			queryTail = tail;
	}

	public void addElement(String name, String prependWord, String dbFieldName,
			String comparison, Object value, boolean isSimpleExprWithParam) {
		if (value != null && !value.equals("")) {
			QueryElement element = new QueryElement();
			element.name = name;
			element.prependWord = prependWord;
			element.fieldValue = value;
			element.comparison = comparison;
			element.dbFieldName = dbFieldName;
			element.simpleExprWithParam = isSimpleExprWithParam;

			queryElements.add(element);
		}
	}

	public void addElementIfNotNull(String name, String prependWord,
			String dbFieldName, String comparison, Object value) {
		if (value != null && !value.equals("")) {
			if (comparison.toUpperCase().equals("IN")) {
				this.addElement(name, prependWord, dbFieldName, comparison,
						value, true);
			} else {
				this.addElement(name, prependWord, dbFieldName, comparison,
						value, false);
			}
		}
	}

	public void addElementIfNotNull(String name, String prependWord,
			String dbFieldName, String comparison, boolean value) {
		if (value) {
			this.addElement(name, prependWord, dbFieldName, comparison, "1",
					false);
		} else {
			this.addElement(name, prependWord, dbFieldName, comparison, "0",
					false);
		}
	}

	public void addElementInt(String name, String prependWord,
			String dbFieldName, String comparison, int value) {
		this.addElement(name, prependWord, dbFieldName, comparison,
				Integer.toString(value), false);
	}

	public void addSimpleExpression(String name, String prependWord,
			String value) {
		QueryElement element = new QueryElement();
		element.simpleExpr = true;
		element.name = null;
		element.prependWord = prependWord;
		element.fieldValue = value;

		queryElements.add(element);
	}

	public int size() {
		return queryElements.size();
	}

	public String getQuery() throws SQLException {
		if (queryBody == null)
			throw new SQLException("Query body should be defined");

		StringBuffer querySB = new StringBuffer(queryBody);
		if (queryElements.size() > 0) {
			if (!isConditionBlockExists)
				querySB.append(" WHERE ");

			int i = 0;
			list.clear();
			for (Iterator<QueryElement> it = queryElements.iterator(); it
					.hasNext();) {
				QueryElement element = it.next();

				if ((i == 0 && isConditionBlockExists) || (i > 0)) {
					querySB.append(' ').append(element.prependWord).append(' ');
				}

				if (element.simpleExpr == false) {
					if (element.simpleExprWithParam) {
						querySB.append(' ').append(element.dbFieldName);
						list.add(list.size(), element);
					} else {
						querySB.append(' ').append(element.dbFieldName)
								.append(' ').append(element.comparison)
								.append(' ').append('?');
						list.add(list.size(), element);
					}
				} else {
					querySB.append(' ').append(element.fieldValue);
				}

				i++;
			}
		}

		if (queryTail != null) {
			querySB.append(' ').append(queryTail);
		}

		return querySB.toString();
	}

	public void updateQueryParameters(PreparedStatement pstmt)
			throws SQLException {
		for (int i = 0; i < list.size(); i++) {
			QueryElement element = list.get(i);
			if (element.fieldValue instanceof Date) {
				pstmt.setTimestamp(i + 1, new java.sql.Timestamp(
						((Date) element.fieldValue).getTime()));
			} else {
				pstmt.setString(
						i + 1,
						element.fieldValue != null ? element.fieldValue
								.toString() : null);
			}
		}
	}

	public String getQueryBody() {
		return queryBody;
	}

	public String getQueryTail() {
		return queryTail;
	}
}
