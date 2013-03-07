package com.yitengls.bean;

import java.sql.Date;

public class TstampBean {
	private int id;
	private String year;
	private Date usedate;
	private String usereason;
	private int filenum;
	private String petitioner;
	private int petitionerid;
	private String memo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getUsedate() {
		return usedate;
	}
	public void setUsedate(Date usedate) {
		this.usedate = usedate;
	}
	public String getUsereason() {
		return usereason;
	}
	public void setUsereason(String usereason) {
		this.usereason = usereason;
	}
	public int getFilenum() {
		return filenum;
	}
	public void setFilenum(int filenum) {
		this.filenum = filenum;
	}
	public String getPetitioner() {
		return petitioner;
	}
	public void setPetitioner(String petitioner) {
		this.petitioner = petitioner;
	}
	public int getPetitionerid() {
		return petitionerid;
	}
	public void setPetitionerid(int petitionerid) {
		this.petitionerid = petitionerid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
