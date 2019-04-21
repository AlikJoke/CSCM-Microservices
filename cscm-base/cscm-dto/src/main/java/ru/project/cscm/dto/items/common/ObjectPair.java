package ru.project.cscm.dto.items.common;


public final class ObjectPair<K, V> {

	private K key;
	private V value;

	public ObjectPair() {
	}

	public ObjectPair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public Object getValueAsObject() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}
