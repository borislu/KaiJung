package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SalesMonthlyPerson database table.
 * 
 */
@Entity
public class SalesMonthlyPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int smpid;

	private float amount;

	private int employeeid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date ondate;

	private String remark;

	private int status;

    public SalesMonthlyPerson() {
    }

	public int getSmpid() {
		return this.smpid;
	}

	public void setSmpid(int smpid) {
		this.smpid = smpid;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public Date getOndate() {
		return this.ondate;
	}

	public void setOndate(Date ondate) {
		this.ondate = ondate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}