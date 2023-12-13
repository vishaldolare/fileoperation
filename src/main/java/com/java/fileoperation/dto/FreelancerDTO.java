package com.java.fileoperation.dto;

import lombok.Data;

@Data
public class FreelancerDTO {
	
	private Integer id;
	
	private String name;
	
	private String location;

	public Integer getId() {
		return id;
	}

	public void setId(Integer d) {
		this.id = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
