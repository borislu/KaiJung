package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CustomerService database table.
 * 
 */
@Entity
public class CustomerService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int serviceid;

	private int counterid;

	private int customerid;

	private int employeeid;

	private String feedback;

    @Temporal( TemporalType.TIMESTAMP)
	private Date ontime;

	private int productid;

	private int status;

	private int tranid;

    public CustomerService() {
    }

	public int getServiceid() {
		return this.serviceid;
	}

	public void setServiceid(int serviceid) {
		this.serviceid = serviceid;
	}

	public int getCounterid() {
		return this.counterid;
	}

	public void setCounterid(int counterid) {
		this.counterid = counterid;
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

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getOntime() {
		return this.ontime;
	}

	public void setOntime(Date ontime) {
		this.ontime = ontime;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTranid() {
		return this.tranid;
	}

	public void setTranid(int tranid) {
		this.tranid = tranid;
	}

}