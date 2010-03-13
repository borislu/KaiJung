package com.kaijung.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Transactions implements Serializable {
	@EmbeddedId
	private TransactionsPK pk;

	private Date ontime;

	private int amount;

	private int payment;

	private int customerid;

	private int employeeid;

	private int planid;

	private String creditcard;

	private String remark;

	private int status;

	private static final long serialVersionUID = 1L;

	public Transactions() {
		super();
	}

	public TransactionsPK getPk() {
		return this.pk;
	}

	public void setPk(TransactionsPK pk) {
		this.pk = pk;
	}

	public Date getOntime() {
		return this.ontime;
	}

	public void setOntime(Date ontime) {
		this.ontime = ontime;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPayment() {
		return this.payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
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

	public int getPlanid() {
		return this.planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getCreditcard() {
		return this.creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
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
