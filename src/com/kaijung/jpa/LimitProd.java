package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the LimitProd database table.
 * 
 */
@Entity
public class LimitProd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int limitprodid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date etime;

	private int productid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date stime;

	private int wareid;

    public LimitProd() {
    }

	public int getLimitprodid() {
		return this.limitprodid;
	}

	public void setLimitprodid(int limitprodid) {
		this.limitprodid = limitprodid;
	}

	public Date getEtime() {
		return this.etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public Date getStime() {
		return this.stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public int getWareid() {
		return this.wareid;
	}

	public void setWareid(int wareid) {
		this.wareid = wareid;
	}

}