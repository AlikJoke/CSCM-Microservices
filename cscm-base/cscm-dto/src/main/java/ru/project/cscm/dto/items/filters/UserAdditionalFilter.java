package ru.project.cscm.dto.items.filters;

public class UserAdditionalFilter {
	
	private String nameAndAddress;
	private String atmID;
	private String extAtmID;
	private int typeByOperations;
		
	public UserAdditionalFilter(){
		typeByOperations = -1;
	}

	public String getAtmID() {
		return atmID;
	}

	public void setAtmID(String atmID) {
		this.atmID = atmID;
	}

	public String getExtAtmID() {
		return extAtmID;
	}

	public void setExtAtmID(String extAtmID) {
		this.extAtmID = extAtmID;
	}

	public String getNameAndAddress() {
		return nameAndAddress;
	}

	public void setNameAndAddress(String nameAndAddress) {
		this.nameAndAddress = nameAndAddress;
	}

	public int getTypeByOperations() {
		return typeByOperations;
	}

	public void setTypeByOperations(int typeByOperations) {
		this.typeByOperations = typeByOperations;
	}
}

