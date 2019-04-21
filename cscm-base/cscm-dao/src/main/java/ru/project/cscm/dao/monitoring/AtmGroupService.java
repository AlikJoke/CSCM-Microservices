package ru.project.cscm.dao.monitoring;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.project.cscm.dao.core.AbstractService;
import ru.project.cscm.dao.core.mappers.AtmGroupMapper;
import ru.project.cscm.dto.items.common.AtmGroup;

@Service
public class AtmGroupService extends AbstractService<AtmGroupMapper> {
	
	@Autowired
	private AtmService atmService;

	public AtmGroup getAtmGroupById(@NotNull Integer id) {
		final AtmGroup group = this.getReadOnlyMapper().getAtmGroupById(id);
		group.getAtms().addAll(atmService.getAtmsByAtmGroup(id));
		
		return group;
	}

	public List<AtmGroup> getAllAtmGroups() {
		final List<AtmGroup> result = this.getReadOnlyMapper().getAllAtmGroups();
		result.forEach(g -> g.getAtms().addAll(this.atmService.getAtmsByAtmGroup(g.getId())));
		
		return result;
	}

	public void save(@NotNull AtmGroup group) {
		if (group.getId() == null) {
			group.setId(this.getMapper().getLastGroupId() + 1);
			
			this.getMapper().createAtmGroup(group.getId(), group.getDescription(), group.getName(), group.getType().getId());
		} else {
		
			this.getMapper().updateAtmGroup(group.getId(), group.getDescription(), group.getName(), group.getType().getId());
			this.getMapper().deleteAtmsFromAtmGroup(group.getId());
		}
		
		group.getAtms().forEach(a -> this.getMapper().addAtmToAtmGroup(group.getId(), a.getId()));
		
		this.threadContext.getSession().commit();
	}

	public void deleteAtmGroupById(@NotNull Integer id) {
		this.getMapper().deleteAtmsFromAtmGroup(id);
		this.getMapper().deleteAtmGroupById(id);
		
		this.threadContext.getSession().commit();
	}

	@Override
	protected Class<AtmGroupMapper> getMapperClass() {
		return AtmGroupMapper.class;
	}
}
