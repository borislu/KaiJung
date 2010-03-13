package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SalesAnnualPerson database table.
 * 
 */
@Entity
public class SalesAnnualPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int sapid;

	private float amount;

	private int employeeid;

    @Temporal( TemporalType.DATE)
	private Date onyear;

	private String remark;

	private int status;

    public SalesAnnualPerson() {
    }

	public int getSapid() {
		return this.sapid;
	}

	public void setSapid(int sapid) {
		this.sapid = sapid;
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

	public Date getOnyear() {
		return this.onyear;
	}

	public void setOnyear(Date onyear) {
		this.onyear = onyear;
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