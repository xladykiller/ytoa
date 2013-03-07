package com.yitengls.bean;

import java.sql.Date;

public class TstampuserviewBean {
	private int id;
	private String year;
	private Date usedate;
	private String usereason;
	private int filenum;
	private String petitioner;
	private int petitionerid;
	private String memo;
	
	private String psw;
	private int powerid;
	private String uname;
	private String cellphone;
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
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public int getPowerid() {
		return powerid;
	}
	public void setPowerid(int powerid) {
		this.powerid = powerid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

}
