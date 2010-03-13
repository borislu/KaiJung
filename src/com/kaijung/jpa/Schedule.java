package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Schedule database table.
 * 
 */
@Entity
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int schid;

	private int counterid;

	private int employeeid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date etime;

	private int executeid;

	private String remark;

    @Temporal( TemporalType.TIMESTAMP)
	private Date retime;

    @Temporal( TemporalType.TIMESTAMP)
	private Date rstime;

	private int status;

    @Temporal( TemporalType.TIMESTAMP)
	private Date stime;

    public Schedule() {
    }

	public int getSchid() {
		return this.schid;
	}

	public void setSchid(int schid) {
		this.schid = schid;
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

	public Date getEtime() {
		return this.etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public int getExecuteid() {
		return this.executeid;
	}

	public void setExecuteid(int executeid) {
		this.executeid = executeid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getRetime() {
		return this.retime;
	}

	public void setRetime(Date retime) {
		this.retime = retime;
	}

	public Date getRstime() {
		return this.rstime;
	}

	public void setRstime(Date rstime) {
		this.rstime = rstime;
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

}