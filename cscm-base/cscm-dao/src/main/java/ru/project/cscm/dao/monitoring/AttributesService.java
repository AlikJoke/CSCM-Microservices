package ru.project.cscm.dao.monitoring;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import ru.project.cscm.dao.core.AbstractService;
import ru.project.cscm.dao.core.mappers.AttributesMapper;
import ru.project.cscm.dto.items.common.AtmGroupAttribute;
import ru.project.cscm.dto.items.common.AtmGroupAttributeDescription;
import ru.project.cscm.dto.items.enums.AtmGroupType;

@Service
public class AttributesService extends AbstractService<AttributesMapper> {

	public List<AtmGroupAttribute> getGroupAttributes(@NotNull Integer groupId) {
		return this.getReadOnlyMapper().getAtmGroupAttributes(groupId);
	}

	public void save(AtmGroupAttribute attribute) {
		if (this.getMapper().getAtmGroupAttributeById(
				attribute.getAttributeId(), attribute.getGroupId()) == null) {
			this.getMapper().createAtmGroupAttribute(
					attribute.getAttributeId(), attribute.getGroupId(),
					attribute.getValue(), attribute.isUsed() ? 1 : 0);
		} else {
			this.getMapper().updateAtmGroupAttribute(
					attribute.getAttributeId(), attribute.getGroupId(),
					attribute.getValue(), attribute.isUsed() ? 1 : 0);
		}
		
		this.threadContext.getSession().commit();
	}
	
	public List<AtmGroupAttributeDescription> getAtmGroupDescriptions(AtmGroupType type) {
		return this.getReadOnlyMapper().getAtmGroupAttributesDescriptions(type.getId());
	}
	
	public void deleteAtmGroupAttributes(AtmGroupAttribute attr) {
		this.getMapper().deleteAtmGroupAttributes(attr.getGroupId(), attr.getAttributeId());
		this.threadContext.getSession().commit();
	}

	@Override
	protected Class<AttributesMapper> getMapperClass() {
		return AttributesMapper.class;
	}

}
