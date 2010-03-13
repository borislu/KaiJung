package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SalesAnnualBrand database table.
 * 
 */
@Entity
public class SalesAnnualBrand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int sabid;

	private float amount;

	private int brandid;

    @Temporal( TemporalType.DATE)
	private Date onyear;

	private String remark;

	private int status;

    public SalesAnnualBrand() {
    }

	public int getSabid() {
		return this.sabid;
	}

	public void setSabid(int sabid) {
		this.sabid = sabid;
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

	public Date getOnyear() {
		return this.onyear;
	}

	public void setOnyear(Date onyear) {
		this.onyear = onyear;
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