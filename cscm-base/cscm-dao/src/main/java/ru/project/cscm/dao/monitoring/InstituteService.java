package ru.project.cscm.dao.monitoring;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import ru.project.cscm.dao.core.AbstractService;
import ru.project.cscm.dao.core.mappers.InstituteMapper;
import ru.project.cscm.dto.items.common.Institute;

@Service
public class InstituteService extends AbstractService<InstituteMapper> {

	public Institute getInstituteById(@NotEmpty String id) {
		return this.getMapper().getInstituteById(id);
	}

	public List<Institute> getAllInstitutes() {
		return this.getMapper().getAllInstitutes();
	}

	public void save(@NotNull Institute inst) {
		if (this.getInstituteById(inst.getId()) == null) {
			this.getMapper().createInstitute(inst.getId(),
					inst.getDescription());
		} else {
			this.getMapper().updateInstitute(inst.getId(),
					inst.getDescription());
		}
		
		this.threadContext.getSession().commit();
	}

	public void deleteAll() {
		this.getMapper().deleteAllInstitutes();
		
		this.threadContext.getSession().commit();
	}

	public void deleteInstituteById(@NotEmpty String id) {
		this.getMapper().deleteInstituteById(id);
		
		this.threadContext.getSession().commit();
	}

	public void deleteInstitute(@NotNull Institute inst) {
		this.deleteInstituteById(inst.getId());
		
		this.threadContext.getSession().commit();
	}

	@Override
	protected Class<InstituteMapper> getMapperClass() {
		return InstituteMapper.class;
	}
}
