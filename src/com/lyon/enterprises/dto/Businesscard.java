package com.lyon.enterprises.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="businesscard")
@XmlAccessorType(XmlAccessType.FIELD)
public class Businesscard {

	private Participant participant = null;	
	private Entity entity = null;
	@XmlElement(name = "doctypeid")
	private List<DoctypeId> doctypeid = new ArrayList<DoctypeId>();
	
	public Participant getParticipant() {
		return participant;
	}
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public List<DoctypeId> getDoctypeid() {
		return doctypeid;
	}
	public void setDoctypeid(List<DoctypeId> doctypeid) {
		this.doctypeid = doctypeid;
	}
}
