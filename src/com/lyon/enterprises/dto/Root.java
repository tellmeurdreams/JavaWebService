package com.lyon.enterprises.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {
	
	@XmlAttribute
	private String version;
	@XmlAttribute
	private String creationdt;
	@XmlElement(name = "businesscard")
	private List<Businesscard> businesscard = new ArrayList<Businesscard>();

	public List<Businesscard> getBusinessCard() {
		return businesscard;
	}

	public void setBusinessCard(List<Businesscard> businessCard) {
		this.businesscard = businessCard;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreationdt() {
		return creationdt;
	}

	public void setCreationdt(String creationdt) {
		this.creationdt = creationdt;
	}
}
