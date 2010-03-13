package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SalesMonthly database table.
 * 
 */
@Entity
public class SalesMonthly implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int monthid;

	private float amount;

    @Temporal( TemporalType.DATE)
	private Date onmonth;

	private int paymentid;

	private String remark;

	private int status;

	private int wareid;

    public SalesMonthly() {
    }

	public int getMonthid() {
		return this.monthid;
	}

	public void setMonthid(int monthid) {
		this.monthid = monthid;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getOnmonth() {
		return this.onmonth;
	}

	public void setOnmonth(Date onmonth) {
		this.onmonth = onmonth;
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