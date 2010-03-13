package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the OpponentPlan database table.
 * 
 */
@Entity
public class OpponentPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int opplanid;

	private String activityname;

    @Temporal( TemporalType.TIMESTAMP)
	private Date edate;

    @Temporal( TemporalType.TIMESTAMP)
	private Date sdate;

	private int status;

    public OpponentPlan() {
    }

	public int getOpplanid() {
		return this.opplanid;
	}

	public void setOpplanid(int opplanid) {
		this.opplanid = opplanid;
	}

	public String getActivityname() {
		return this.activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public Date getEdate() {
		return this.edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Date getSdate() {
		return this.sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}