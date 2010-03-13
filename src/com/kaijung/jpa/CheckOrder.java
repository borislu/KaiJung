package com.kaijung.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CheckOrder implements Serializable {
	@Id
	private int checkid;

	private Date ontime;

	private int employeeid;

	private int status;

	private static final long serialVersionUID = 1L;

	public CheckOrder() {
		super();
	}

	public int getCheckid() {
		return this.checkid;
	}

	public void setCheckid(int checkid) {
		this.checkid = checkid;
	}

	public Date getOntime() {
		return this.ontime;
	}

	public void setOntime(Date ontime) {
		this.ontime = ontime;
	}

	public int getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
