package com.example.personapp.vo;

public class PersonVO {
	
	private Long id;
	private String name;
	private String address;
	private Long ammount;
	
	public PersonVO() {}
	
	public PersonVO(String name, String address, Long amm) {
		this.name = name;
		this.address = address;
		this.ammount = amm;
	}
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Long getAmmount() {
		return ammount;
	}

	public void setAmmount(Long ammount) {
		this.ammount = ammount;
	}
	
	

}
