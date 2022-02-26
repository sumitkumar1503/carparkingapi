package com.lc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CarParking {
	@Id
	@GeneratedValue
	Integer id;
	
	String carnumber;
	Integer slotid;
	Date allotmentdate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public Integer getSlotid() {
		return slotid;
	}
	public void setSlotid(Integer slotid) {
		this.slotid = slotid;
	}
	public Date getAllotmentdate() {
		return allotmentdate;
	}
	public void setAllotmentdate(Date allotmentdate) {
		this.allotmentdate = allotmentdate;
	}
	public CarParking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
