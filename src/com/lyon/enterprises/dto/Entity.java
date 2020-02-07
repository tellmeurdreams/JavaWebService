package com.lyon.enterprises.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Entity {

	@XmlAttribute
	private String countrycode;	
	private Name name;
	@XmlElement(name = "id")
	private List<Id> id = new ArrayList<Id>();
	
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public List<Id> getId() {
		return id;
	}
	public void setId(List<Id> id) {
		this.id = id;
	}
}
