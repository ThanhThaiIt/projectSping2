package com.javaweb.model;

import java.sql.Date;

public class BuildingDTO {
	
	private String name;
	private String address;
	private Integer numberOfBasement;
	private String managerNameString;
	private String managerPhoneNumber;
	private int floorarea;
	private String rentArea;
	private int leftArea;
	private int rentprice;
	private String servicefee;
	private Double brokeragefee;
	private Date createddate;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getManagerNameString() {
		return managerNameString;
	}
	public void setManagerNameString(String managerNameString) {
		this.managerNameString = managerNameString;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}
	public int getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(int floorarea) {
		this.floorarea = floorarea;
	}
	public int getLeftArea() {
		return leftArea;
	}
	public void setLeftArea(int leftArea) {
		this.leftArea = leftArea;
	}
	public int getRentprice() {
		return rentprice;
	}
	public void setRentprice(int rentprice) {
		this.rentprice = rentprice;
	}
	public String getServicefee() {
		return servicefee;
	}
	public void setServicefee(String servicefee) {
		this.servicefee = servicefee;
	}
	public Double getBrokeragefee() {
		return brokeragefee;
	}
	public void setBrokeragefee(Double brokeragefee) {
		this.brokeragefee = brokeragefee;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	
	
	
	
}
