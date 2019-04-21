package ru.project.cscm.dao.core;

public interface HasMapper<T extends Mapper> {

	T getMapper();
	
	T getReadOnlyMapper();
}
