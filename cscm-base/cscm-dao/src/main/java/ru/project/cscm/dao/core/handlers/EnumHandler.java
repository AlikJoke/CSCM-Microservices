package ru.project.cscm.dao.core.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import ru.project.cscm.dto.items.common.CmUtils;
import ru.project.cscm.dto.items.enums.AtmCassetteType;

public class EnumHandler implements TypeHandler<Enum<?>> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Enum<?> parameter, JdbcType jdbcType) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enum<?> getResult(ResultSet rs, String columnName) throws SQLException {
		Integer arg = rs.getInt(columnName);
		return CmUtils.getEnumValueById(AtmCassetteType.class, arg);
	}

	@Override
	public Enum<?> getResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer arg = rs.getInt(columnIndex);
		return CmUtils.getEnumValueById(AtmCassetteType.class, arg);
	}

	@Override
	public Enum<?> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
