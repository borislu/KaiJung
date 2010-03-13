package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the OpponentAnnual database table.
 * 
 */
@Entity
public class OpponentAnnual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int oppyid;

	private float amount;

	private int depid;

	private String desc;

	private String name;

    @Temporal( TemporalType.DATE)
	private Date onyear;

	private int status;

    public OpponentAnnual() {
    }

	public int getOppyid() {
		return this.oppyid;
	}

	public void setOppyid(int oppyid) {
		this.oppyid = oppyid;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getDepid() {
		return this.depid;
	}

	public void setDepid(int depid) {
		this.depid = depid;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getOnyear() {
		return this.onyear;
	}

	public void setOnyear(Date onyear) {
		this.onyear = onyear;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}