package ru.project.cscm.dao.core.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import ru.project.cscm.dto.items.common.CmUtils;

public class CachOutHoursFromLastWithdrawalHandler implements
		TypeHandler<Integer> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Integer parameter,
			JdbcType jdbcType) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer getResult(ResultSet rs, String columnName)
			throws SQLException {
		Timestamp arg = rs.getTimestamp(columnName);
		return CmUtils.getHoursBetweenTwoDates(arg, new Date());
	}

	@Override
	public Integer getResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp arg = rs.getTimestamp(columnIndex);
		return CmUtils.getHoursBetweenTwoDates(arg, new Date());
	}

	@Override
	public Integer getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		throw new UnsupportedOperationException();
	}
}
