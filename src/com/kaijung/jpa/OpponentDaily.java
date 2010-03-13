package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the OpponentDaily database table.
 * 
 */
@Entity
public class OpponentDaily implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int oppdid;

	private float amount;

	private int depid;

	private String desc;

	private String name;

    @Temporal( TemporalType.DATE)
	private Date ondate;

	private int status;

    public OpponentDaily() {
    }

	public int getOppdid() {
		return this.oppdid;
	}

	public void setOppdid(int oppdid) {
		this.oppdid = oppdid;
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

	public Date getOndate() {
		return this.ondate;
	}

	public void setOndate(Date ondate) {
		this.ondate = ondate;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}