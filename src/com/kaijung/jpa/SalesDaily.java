package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SalesDaily database table.
 * 
 */
@Entity
public class SalesDaily implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int dayid;

	private float amount;

    @Temporal( TemporalType.DATE)
	private Date ondate;

	private int paymentid;

	private String remark;

	private int status;

	private int wareid;

    public SalesDaily() {
    }

	public int getDayid() {
		return this.dayid;
	}

	public void setDayid(int dayid) {
		this.dayid = dayid;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getOndate() {
		return this.ondate;
	}

	public void setOndate(Date ondate) {
		this.ondate = ondate;
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