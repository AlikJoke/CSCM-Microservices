package ru.project.cscm.dao.core.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public final class DoubleToBooleanTypeHandler implements TypeHandler<Boolean> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
		return Math.round(rs.getDouble(columnName)) == 0;
	}

	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		return Math.round(rs.getDouble(columnIndex)) == 0;
	}

	@Override
	public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}
}
