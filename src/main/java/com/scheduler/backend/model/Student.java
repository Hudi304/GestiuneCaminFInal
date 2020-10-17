package com.scheduler.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "students")

public class Student extends AbstractEntity {
	
	//@Id
	//@GeneratedValue 
	private static final long serialVersionUID = 1L;
	@Column
	private String studentID;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column (name="sex")
	private String  sex;
	@Column
	private Integer roomNr;
	@Column
	private String faculty;
	@Column
	private String email;
	@Column
	private Float media;
	
	
	public String getEmail() {
		return email;
	}
	public Float getMedia() {
		return media;
	}
	public void setMedia(Float media) {
		this.media = media;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRoomNr(Integer roomNr) { 
		this.roomNr = roomNr;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getYearStudy() {
		return yearStudy;
	}
	public void setYearStudy(String yearStudy) {
		this.yearStudy = yearStudy;
	}
	@Column
	private String yearStudy;
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Integer getRoomNr() {
		return roomNr;
	}
	public void setRoomNr(int roomNr) {
		this.roomNr = roomNr;
	}
}
