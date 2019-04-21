package ru.project.cscm.dto.items.common;

import java.util.ArrayList;
import java.util.List;

import ru.project.cscm.dto.items.core.IdentifiableObject;
import ru.project.cscm.dto.items.enums.AtmGroupType;

public final class AtmGroup implements IdentifiableObject<Integer>,
		Comparable<AtmGroup> {

	private Integer id;
	private String name;
	private String description;
	private AtmGroupType type;
	private List<Atm> atms;

	public AtmGroup(Integer atmGroupId, String name, String description, Integer typeId) {
		this.id = atmGroupId;
		this.name = name;
		this.description = description;
		this.type = AtmGroupType.getAtmGroupType(typeId);
	}

	@Override
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public List<Atm> getAtms() {
		if (this.atms == null) {
			this.atms = new ArrayList<>();
		}
		
		return this.atms;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(AtmGroup obj) {
		return CmUtils.compareIdentifiables(this, obj);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof AtmGroup && id == ((AtmGroup) obj).getId();
	}

	public AtmGroupType getType() {
		return type;
	}

	public void setType(AtmGroupType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
