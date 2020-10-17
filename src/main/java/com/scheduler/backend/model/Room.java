package com.scheduler.backend.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")

public class Room extends AbstractEntity  {

	
	private static final long serialVersionUID = 1L;
	@Column
	private Integer roomNr;
	@Column
	private Integer studentsNr;
	@Column
	private String orientation;
	
	
	
	public Integer getRoomNr() {
		return roomNr;
	}
	public void setRoomNr(Integer roomNr) {
		this.roomNr = roomNr;
	}
	public Integer getStudentsNr() {
		return studentsNr;
	}
	public void setStudentsNr(Integer studentsNr) {
		this.studentsNr = studentsNr;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

}
