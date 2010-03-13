package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SalesAnnual database table.
 * 
 */
@Entity
public class SalesAnnual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int yearid;

	private float amount;

    @Temporal( TemporalType.DATE)
	private Date onyear;

	private int paymentid;

	private String remark;

	private int status;

	private int wareid;

    public SalesAnnual() {
    }

	public int getYearid() {
		return this.yearid;
	}

	public void setYearid(int yearid) {
		this.yearid = yearid;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getOnyear() {
		return this.onyear;
	}

	public void setOnyear(Date onyear) {
		this.onyear = onyear;
	}

	public int getPaymentid() {
		return this.paymentid;
	}

	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
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

	public int getWareid() {
		return this.wareid;
	}

	public void setWareid(int wareid) {
		this.wareid = wareid;
	}

}