package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HistoryIndex database table.
 * 
 */
@Entity
public class HistoryIndex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int historyid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date etime;

	private String loginid;

	private String maintable;

	private String remark;

	private int status;

    @Temporal( TemporalType.TIMESTAMP)
	private Date stime;

	private String tablename;

    @Temporal( TemporalType.TIMESTAMP)
	private Date updatime;

    public HistoryIndex() {
    }

	public int getHistoryid() {
		return this.historyid;
	}

	public void setHistoryid(int historyid) {
		this.historyid = historyid;
	}

	public Date getEtime() {
		return this.etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public String getLoginid() {
		return this.loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getMaintable() {
		return this.maintable;
	}

	public void setMaintable(String maintable) {
		this.maintable = maintable;
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

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public Date getUpdatime() {
		return this.updatime;
	}

	public void setUpdatime(Date updatime) {
		this.updatime = updatime;
	}

}