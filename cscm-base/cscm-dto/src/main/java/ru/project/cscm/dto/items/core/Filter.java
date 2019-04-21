package ru.project.cscm.dto.items.core;

public interface Filter<T> {

	T getValue();

	String getLabel();

	boolean isChecked();

	void setChecked(boolean checked);

	String getDescx();
}
