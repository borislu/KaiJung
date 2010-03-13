package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SalesGoal database table.
 * 
 */
@Entity
public class SalesGoal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int goalid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date etime;

	private float goal;

	private String remark;

	private int status;

    @Temporal( TemporalType.TIMESTAMP)
	private Date stime;

	private int targetid;

	private int type;

    public SalesGoal() {
    }

	public int getGoalid() {
		return this.goalid;
	}

	public void setGoalid(int goalid) {
		this.goalid = goalid;
	}

	public Date getEtime() {
		return this.etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public float getGoal() {
		return this.goal;
	}

	public void setGoal(float goal) {
		this.goal = goal;
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

	public Date getStime() {
		return this.stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public int getTargetid() {
		return this.targetid;
	}

	public void setTargetid(int targetid) {
		this.targetid = targetid;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

}