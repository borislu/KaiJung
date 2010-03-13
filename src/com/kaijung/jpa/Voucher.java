package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Voucher database table.
 * 
 */
@Entity
public class Voucher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int voucherid;

	private float amount;

	private int counterid;

	private int employeeid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date ondate;

	private int payment;

    public Voucher() {
    }

	public int getVoucherid() {
		return this.voucherid;
	}

	public void setVoucherid(int voucherid) {
		this.voucherid = voucherid;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getCounterid() {
		return this.counterid;
	}

	public void setCounterid(int counterid) {
		this.counterid = counterid;
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

	public int getPayment() {
		return this.payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

}