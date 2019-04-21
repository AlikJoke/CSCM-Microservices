package ru.project.cscm.dao.core.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class FormatStringTypeHandler implements TypeHandler<String> {

	@Override
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getString(columnName).replaceAll("\n", "");
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getString(columnIndex).replaceAll("\n", "");
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
