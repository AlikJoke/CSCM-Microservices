package ru.project.cscm.dao.monitoring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import ru.project.cscm.dao.core.AbstractService;
import ru.project.cscm.dao.core.mappers.GroupActualStateMapper;
import ru.project.cscm.dao.utils.DaoUtils;
import ru.project.cscm.dto.items.common.AtmGroup;
import ru.project.cscm.dto.items.enums.EncashmentActionType;

import com.google.common.base.Optional;

@Service
public class GroupAtmStateService extends AbstractService<GroupActualStateMapper> {
	
	public List<ImmutablePair<String, String>> getAtmEncPlanSums(int atmId, Date date) {
		return Optional
				.fromNullable(
						getMapper().getAtmEncPlanSums(
								DaoUtils.getSqlDate(date), atmId)).or(
						new ArrayList<ImmutablePair<String, String>>());
	}

	public List<ImmutablePair<String, String>> getAtmEncPeriodSums(int atmId, Date dateFrom, Date dateTo) {
		return Optional.fromNullable(
				getMapper().getAtmEncPeriodSums(DaoUtils.getSqlDate(dateFrom),
						DaoUtils.getSqlDate(dateFrom), atmId)).or(
				new ArrayList<ImmutablePair<String, String>>());
	}

	public List<ImmutablePair<String, String>> getAtmRemainingSums(int atmId) {
		return Optional.fromNullable(getMapper().getAtmRemainingSums(atmId))
				.or(new ArrayList<ImmutablePair<String, String>>());
	}

	public List<ImmutablePair<String, String>> getAtmEncStatSums(int atmId, Date date, EncashmentActionType actionType) {
		return Optional.fromNullable(
				getMapper().getAtmEncStatSums(DaoUtils.getSqlDate(date), atmId,
						actionType.getId())).or(
				new ArrayList<ImmutablePair<String, String>>());
	}

	public List<ImmutablePair<String, String>> getAtmsEncPlanSums(Date date) {
		return Optional.fromNullable(
					getMapper().getAtmsEncPlanSums(DaoUtils.getSqlDate(date)))
					.or(new ArrayList<ImmutablePair<String, String>>());
	}

	public List<ImmutablePair<String, String>> getAtmsEncPeriodSums(Date dateFrom, Date dateTo) {
		return Optional.fromNullable(
					getMapper().getAtmsEncPeriodSums(DaoUtils.getSqlDate(dateFrom),
							DaoUtils.getSqlDate(dateTo))).or(
					new ArrayList<ImmutablePair<String, String>>());
	}

	public List<ImmutablePair<String, String>> getAtmsRemainingSums() {
		return Optional.fromNullable(
					getMapper().getAtmsRemainingSums())
					.or(new ArrayList<ImmutablePair<String, String>>());
	}

	public List<ImmutablePair<String, String>> getAtmsEncStatSums(Date date,
			EncashmentActionType actionType) {
		return Optional.fromNullable(
					getMapper().getAtmsEncStatSums(DaoUtils.getSqlDate(date),
							actionType.getId())).or(new ArrayList<ImmutablePair<String, String>>());
	}
	
	public AtmGroup getAtmGroup(String id) {
		return getMapper().getAtmGroup(id);
	}

	@Override
	protected Class<GroupActualStateMapper> getMapperClass() {
		return GroupActualStateMapper.class;
	}

}
