package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Transactions database table.
 * 
 */
@Entity
@Table(name="Transactions")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TransactionPK id;

	private int amount;

	private String creditcard;

	private int customerid;

	private int employeeid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date ontime;

	private int payment;

	private int planid;

	private String remark;

	private int status;

    public Transaction() {
    }

	public TransactionPK getId() {
		return this.id;
	}

	public void setId(TransactionPK id) {
		this.id = id;
	}
	
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCreditcard() {
		return this.creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public int getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public int getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public Date getOntime() {
		return this.ontime;
	}

	public void setOntime(Date ontime) {
		this.ontime = ontime;
	}

	public int getPayment() {
		return this.payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public int getPlanid() {
		return this.planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
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