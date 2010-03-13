package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SalesDailyBrand database table.
 * 
 */
@Entity
public class SalesDailyBrand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int sdbid;

	private float amount;

	private int brandid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date ondate;

	private String remark;

	private int status;

    public SalesDailyBrand() {
    }

	public int getSdbid() {
		return this.sdbid;
	}

	public void setSdbid(int sdbid) {
		this.sdbid = sdbid;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getBrandid() {
		return this.brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public Date getOndate() {
		return this.ondate;
	}

	public void setOndate(Date ondate) {
		this.ondate = ondate;
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

}