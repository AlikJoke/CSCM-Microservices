package ru.project.cscm.dao.core.mappers.builders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ru.project.cscm.dao.utils.DaoUtils;

public class AtmBuilder {

	@SuppressWarnings("unchecked")
	public String builderQueryFilter(Map<String, Object> params) {
		List<Integer> atmList = (List<Integer>) params.get("atmsIds");
		List<Integer> groupList = (List<Integer>) params.get("groupsIds");

		StringBuilder sb = new StringBuilder();

		if (groupList != null && !groupList.isEmpty()) {
			sb.append("select a.atm_id, name, street, city, state, inst_id, external_atm_id from T_CM_ATM a join T_CM_ATM2ATM_GROUP a2ag on a.atm_id = a2ag.atm_id where ");
			sb.append(DaoUtils.generateInConditionNumber("atm_group_id",
					groupList));
			sb.append(" AND ");
		} else {
			sb.append("select a.atm_id, name, street, city, state, inst_id, external_atm_id from T_CM_ATM a where ");
		}

		if (atmList != null && !atmList.isEmpty()) {
			sb.append(DaoUtils.generateInConditionNumber("a.atm_id", atmList));
		} else {
			sb.append(DaoUtils.generateInConditionNumber("a.atm_id",
					Arrays.asList(Integer.MAX_VALUE)));
		}

		sb.append(" ORDER BY ATM_ID");
		return sb.toString();
	}
}
