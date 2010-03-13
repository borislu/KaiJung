package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CheckStock database table.
 * 
 */
@Entity
public class CheckStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int checkstockid;

	private int brandid;

	private int employeeid;

	private String massid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date ontime;

	private int status;

    public CheckStock() {
    }

	public int getCheckstockid() {
		return this.checkstockid;
	}

	public void setCheckstockid(int checkstockid) {
		this.checkstockid = checkstockid;
	}

	public int getBrandid() {
		return this.brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public int getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getMassid() {
		return this.massid;
	}

	public void setMassid(String massid) {
		this.massid = massid;
	}

	public Date getOntime() {
		return this.ontime;
	}

	public void setOntime(Date ontime) {
		this.ontime = ontime;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}