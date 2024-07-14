package com.javaweb.repository.entity;

import java.sql.Date;

public class BuildingEntity {
	private Long id;
	private String name;
	private String street;
	private String ward;
	private Long districtid;
	private int numberofbasement;
	private String managername;
	private String managerphonenumber;
	private int floorarea;
	private int rentprice;
	private String servicefee;
	private Double brokeragefee;
	private Date createddate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public Long getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}

	public int getNumberofbasement() {
		return numberofbasement;
	}

	public void setNumberofbasement(int numberofbasement) {
		this.numberofbasement = numberofbasement;
	}

	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public String getManagerphonenumber() {
		return managerphonenumber;
	}

	public void setManagerphonenumber(String managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
	}

	public int getFloorarea() {
		return floorarea;
	}

	public void setFloorarea(int floorarea) {
		this.floorarea = floorarea;
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

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

}
