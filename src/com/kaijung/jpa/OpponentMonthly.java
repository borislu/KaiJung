package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the OpponentMonthly database table.
 * 
 */
@Entity
public class OpponentMonthly implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int oppmid;

	private float amount;

	private int depid;

	private String desc;

	private String name;

    @Temporal( TemporalType.DATE)
	private Date onmonth;

	private int status;

    public OpponentMonthly() {
    }

	public int getOppmid() {
		return this.oppmid;
	}

	public void setOppmid(int oppmid) {
		this.oppmid = oppmid;
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

	public Date getOnmonth() {
		return this.onmonth;
	}

	public void setOnmonth(Date onmonth) {
		this.onmonth = onmonth;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}