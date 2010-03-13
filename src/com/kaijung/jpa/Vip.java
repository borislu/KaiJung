package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Vip database table.
 * 
 */
@Entity
public class Vip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int vipid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date etime;

	private int level;

	private String status;

    @Temporal( TemporalType.TIMESTAMP)
	private Date stime;

    public Vip() {
    }

	public int getVipid() {
		return this.vipid;
	}

	public void setVipid(int vipid) {
		this.vipid = vipid;
	}

	public Date getEtime() {
		return this.etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStime() {
		return this.stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

}