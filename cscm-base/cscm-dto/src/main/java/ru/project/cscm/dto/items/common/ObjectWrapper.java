package ru.project.cscm.dto.items.common;

public final class ObjectWrapper<T> {

	private T object;

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public ObjectWrapper() {
	}

	public ObjectWrapper(T object) {
		super();
		this.object = object;
	}
}