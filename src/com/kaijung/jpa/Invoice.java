package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Invoice database table.
 * 
 */
@Entity
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int invoiceid;

	@Column(name="counter_assigned")
	private int counterAssigned;

	private int counterid;

	private String invoiceno;

	private int status;

	private int tranid;

    public Invoice() {
    }

	public int getInvoiceid() {
		return this.invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public int getCounterAssigned() {
		return this.counterAssigned;
	}

	public void setCounterAssigned(int counterAssigned) {
		this.counterAssigned = counterAssigned;
	}

	public int getCounterid() {
		return this.counterid;
	}

	public void setCounterid(int counterid) {
		this.counterid = counterid;
	}

	public String getInvoiceno() {
		return this.invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
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