package com.javaweb.repository.entity;

public class RentAreaEntity {
	private long id;
	public String value;
	public long buildingid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public long getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(long buildingid) {
		this.buildingid = buildingid;
	}
	
}
